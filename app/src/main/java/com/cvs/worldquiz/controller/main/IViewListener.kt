package com.cvs.worldquiz.controller.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.ext.slide.SlideFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface IViewListener {

    fun onLaunchScreenPassed()

    fun showDiscoverFragment()

    fun showRulesFragment()

}

open class BaseFragment : SlideFragment() {

    protected lateinit var owner: IViewListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        owner = context as IViewListener
    }

}