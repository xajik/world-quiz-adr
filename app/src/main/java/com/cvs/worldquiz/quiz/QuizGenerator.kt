package com.cvs.worldquiz.quiz

import android.content.Context
import android.text.TextUtils

import com.cvs.worldquiz.db.model.Country

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Random

import io.realm.RealmResults

/**
 * @author IgorSteblii on 13.11.16.
 */

class QuizGenerator(private val mContext: Context,
                    private val mCountries: RealmResults<Country>, questionTypes: HashSet<Int>) {

    private val mRandom: Random = Random()
    private val mUsedCountries: MutableList<String>
    private val questionTypes: List<QuizQuestionType>

    init {
        mUsedCountries = ArrayList()
        this.questionTypes = getQuestionTypes(questionTypes)
    }

    fun generateQuestion(): Question {
        val questionTypeIndex = mRandom.nextInt(questionTypes.size)
        val questionType = questionTypes[questionTypeIndex]
        var country: Country? = null
        var mCountryIndex: Int
        var questionMessage: String? = null
        var correctAnswer: String? = null
        val answers = ArrayList<String>(4)
        do {
            do {
                do {
                    mCountryIndex = mRandom.nextInt(mCountries.size)
                    country = mCountries[mCountryIndex]
                } while (mUsedCountries.contains(country!!.label))
                questionMessage = questionType.getQuestionMessage(mContext, country).trim()
            } while (TextUtils.isEmpty(questionMessage))
            correctAnswer = questionType.getAnswerColumn(country).toString().trim()
        } while (TextUtils.isEmpty(correctAnswer))
        answers.add(correctAnswer!!)
        mUsedCountries.add(country!!.label!!)
        for (i in 0 until FAKE_ANSWER_COUNT) {
            var answer: String
            do {
                val index = mRandom.nextInt(mCountries.size)
                answer = questionType.getAnswerColumn(mCountries[index]).toString()
            } while (TextUtils.isEmpty(answer) || answers.contains(answer))
            answers.add(answer)
        }
        Collections.shuffle(answers, mRandom)
        return Question(questionMessage!!, correctAnswer, answers)
    }

    private fun getQuestionTypes(questionIds: HashSet<Int>): List<QuizQuestionType> {
        if (questionIds.isNotEmpty()) {
            val list = ArrayList<QuizQuestionType>()
            for (id in questionIds) {
                val type = QuizQuestionType.getById(id)
                list.add(type)
            }
            return list
        }
        return Arrays.asList(*QuizQuestionType.values())
    }

    companion object {

        private val FAKE_ANSWER_COUNT = 3
    }
}
