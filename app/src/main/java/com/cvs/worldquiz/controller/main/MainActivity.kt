package com.cvs.worldquiz.controller.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.main.discover.DiscoverFragment
import com.cvs.worldquiz.controller.main.rules.RulesFragment
import com.r0adkll.slidr.Slidr

class MainActivity : AppCompatActivity(), IViewListener, IFragmentOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(SplashScreenFragment::class)
    }

    override fun onLaunchScreenPassed() {
        switchFragment(MenuFragment::class)
    }

    override fun showDiscoverFragment() {
        addFragment(DiscoverFragment::class)
    }

    override fun showRulesFragment() {
        addFragment(RulesFragment::class)
    }

}
