package com.cvs.worldquiz.controller.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.cvs.worldquiz.R
import com.cvs.worldquiz.quiz.QuizQuestionType

class GameStartFragment : BaseGameFragment(), View.OnClickListener {

    companion object {
        private const val SCORE_ARGS = "score_args"
    }

    private val questions: HashSet<Int> = HashSet()
    private var mTitleView: TextView? = null
    private var mMaxScore: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quiz_start, container, false)
        setupUI(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        mTitleView?.text = getString(R.string.start_quiz)
        arguments?.getLong(SCORE_ARGS)?.let {
            mTitleView?.text = getString(R.string.you_failed_with_points, it)
        }
        updateMaxScore()
    }

    private fun updateMaxScore() {
        mMaxScore?.text = owner.getMaxScore().toString()
    }

    private fun setupUI(view: View) {
        mTitleView = view.findViewById(R.id.quiz_start_title)
        mMaxScore = view.findViewById(R.id.quiz_start_max_score_value)
        view.findViewById<CheckBox>(R.id.quiz_start_setting_area).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.quiz_start_setting_population).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.quiz_start_setting_capital).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.quiz_start_setting_capital_of).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.quiz_start_setting_continent).setOnClickListener(this)
        view.findViewById<Button>(R.id.quiz_start_action_button).setOnClickListener {
            owner.startGame(questions)
        }
    }

    override fun onClick(v: View?) {
        var option = 0
        when (v?.id) {
            R.id.quiz_start_setting_area -> option = QuizQuestionType.AREA_OF.id
            R.id.quiz_start_setting_population -> option = QuizQuestionType.POPULATION_OF.id
            R.id.quiz_start_setting_capital -> option = QuizQuestionType.CAPITAL_OF.id
            R.id.quiz_start_setting_capital_of -> option = QuizQuestionType.CITY_IS_CAPITAL.id
            R.id.quiz_start_setting_continent -> option = QuizQuestionType.CONTINENT_OF.id
        }
        if (questions.contains(option)) {
            questions.remove(option)
        } else {
            questions.add(option)
        }
    }

    fun setScore(score: Long) {
        arguments?.putLong(SCORE_ARGS, score)
        mTitleView?.text = getString(R.string.you_failed_with_points, score)
        updateMaxScore()
    }

}