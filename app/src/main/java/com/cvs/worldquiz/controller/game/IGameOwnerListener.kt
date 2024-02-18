package com.cvs.worldquiz.controller.game

import android.content.Context
import android.support.v4.app.Fragment

interface IGameOwnerListener {

    fun startGame(questions: HashSet<Int>)

    fun gameFailed(score: Long)

    fun getMaxScore(): Long

}

open class BaseGameFragment : Fragment() {

    lateinit var owner: IGameOwnerListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        owner = context as IGameOwnerListener
    }

}