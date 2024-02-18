package com.cvs.worldquiz.controller.game

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.Slide
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.main.IFragmentOwner
import com.cvs.worldquiz.controller.main.addFragment
import com.cvs.worldquiz.controller.main.switchFragment
import com.r0adkll.slidr.Slidr

class GameActivity : AppCompatActivity(), IGameOwnerListener, IFragmentOwner {

    lateinit var preferences: GamePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //slide to close
        Slidr.attach(this)
        preferences = GamePreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()
        switchFragment(GameStartFragment::class)
    }

    override fun startGame(questions: HashSet<Int>) {
        addFragment(GamePlayFragment.newInstance(questions))
    }

    override fun gameFailed(score: Long) {
        supportFragmentManager.popBackStack()
        val fragment = supportFragmentManager.findFragmentByTag(GameStartFragment::class.simpleName)
                as GameStartFragment?
        updateMaxScore(score)
        if (fragment != null) {
            fragment.setScore(score)
        }
    }

    private fun updateMaxScore(score: Long) {
        if (preferences.maxScore < score) {
            preferences.maxScore = score
        }
    }

    override fun getMaxScore(): Long {
        return preferences.maxScore
    }

}