package com.cvs.worldquiz.controller.game

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ProgressBar
import android.widget.TextView
import com.cvs.worldquiz.R
import com.cvs.worldquiz.db.Database
import com.cvs.worldquiz.db.model.Country
import com.cvs.worldquiz.quiz.Question
import com.cvs.worldquiz.quiz.QuizGenerator
import java.util.*

class GamePlayFragment : BaseGameFragment(), View.OnClickListener {

    companion object {
        private const val QUESTIONS_ARGS = "questions_args"

        fun newInstance(questions: HashSet<Int>): GamePlayFragment {
            val fragment = GamePlayFragment()
            val bundle = Bundle()
            bundle.putSerializable(QUESTIONS_ARGS, questions)
            fragment.arguments = bundle
            return fragment
        }

    }

    private lateinit var quizGenerator: QuizGenerator
    private var questionViews = mutableListOf<TextView>()
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var progressView: ProgressBar
    private lateinit var progressTextView: TextView
    private var timer: CountDownTimer? = null
    private var score: Long = 0
        set(value) {
            scoreTextView.text = value.toString()
            field = value
        }

    private var timeLeft: Long? = null
    private var gameOver = false
    private var question: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val questions = arguments?.getSerializable(QUESTIONS_ARGS) as HashSet<Int>
        quizGenerator = QuizGenerator(context!!, Database.shared.getAll(Country::class), questions)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quiz_play, container, false)
        setupUi(view)
        return view
    }

    private fun setupUi(view: View) {
        questionTextView = view.findViewById(R.id.quiz_question)
        scoreTextView = view.findViewById(R.id.quiz_point_value)
        progressTextView = view.findViewById(R.id.quiz_progress_timer)
        progressView = view.findViewById(R.id.quiz_progress)
        addQuestionView(view, R.id.quiz_answer_bottom_right)
        addQuestionView(view, R.id.quiz_answer_bottom_left)
        addQuestionView(view, R.id.quiz_answer_top_right)
        addQuestionView(view, R.id.quiz_answer_top_left)
    }

    private fun addQuestionView(view: View, id: Int) {
        val textView = view.findViewById<TextView>(id)
        textView.setOnClickListener(this)
        questionViews.add(textView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateQuestion()
    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
        timer = null
    }

    private fun generateQuestion() {
        question = quizGenerator.generateQuestion()
        questionTextView.text = question?.question
        questionViews.forEachIndexed { i, view ->
            view.text = question!!.answers[i]
        }
    }

    private fun startTimer() {
        progressView.max = 60
        timeLeft = timeLeft ?: 60000
        timer = object : CountDownTimer(timeLeft!!, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val pointLeft: Int = (millisUntilFinished / 1000).toInt()
                progressView.progress = pointLeft
                progressTextView.text = pointLeft.toString()
            }

            override fun onFinish() {
                timeLeft = null
                gameOver(null, null)
            }
        }
        timer?.start()
    }

    override fun onClick(v: View?) {
        if (gameOver) {
            return
        }
        val text = (v as TextView).text
        timer?.cancel()
        timer = null
        timeLeft = null
        if (Objects.deepEquals(text, question?.answer)) {
            score = score.inc()
            generateQuestion()
            startTimer()
        } else {
            var answer: View? = null
            questionViews.forEach {
                if (Objects.deepEquals(it.text, question?.answer)) {
                    answer = it
                }
            }
            gameOver(v, answer!!)
        }
    }

    private fun gameOver(view: View?, answer: View?) {
        gameOver = true
        if (view == null) {
            owner.gameFailed(score)
            return
        }

        answer?.setBackgroundResource(R.drawable.shape_rounded_green)

        val animation = AlphaAnimation(1f, 0.3f)
        animation.repeatCount = 3
        animation.duration = 500
        animation.repeatMode = Animation.REVERSE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                owner.gameFailed(score)
            }

        })
        view.setBackgroundResource(R.drawable.shape_rounded_red)
        view.startAnimation(animation)
    }

}