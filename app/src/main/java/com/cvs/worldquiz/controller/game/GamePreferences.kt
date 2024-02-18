package com.cvs.worldquiz.controller.game

import android.content.Context

class GamePreferences(context: Context) {

    companion object {
        private const val PREFERENCE_NAME = "quiz.game.preference"
        private const val MAX_SCORE_PREFERENCE = "quiz.game.preference.max.score"
    }

    private val preferences = context.getSharedPreferences(PREFERENCE_NAME, 0)

    var maxScore: Long
        get() = preferences.getLong(MAX_SCORE_PREFERENCE, 0)
        set(value) {
            preferences.edit().putLong(MAX_SCORE_PREFERENCE, value).apply()
        }

}