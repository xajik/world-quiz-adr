package com.cvs.worldquiz.controller.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.game.GameActivity
import kotlin.reflect.KClass

class MenuFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View) {
        setPlayMenuButton(view, R.id.menu_play, GameActivity::class)
        setDiscoverMenu(view)
        setRulesMenu(view)
    }

    private fun setDiscoverMenu(view: View) {
        view.findViewById<View>(R.id.menu_discover)
                .setOnClickListener {
                    owner.showDiscoverFragment()
                }
    }

    private fun setRulesMenu(view: View) {
        view.findViewById<View>(R.id.menu_rules)
                .setOnClickListener {
                    owner.showRulesFragment()
                }
    }

    private fun setPlayMenuButton(view: View, id: Int, clazz: KClass<*>) {
        val play = view.findViewById<TextView>(id)
        play.setOnClickListener {
            val i = Intent(this@MenuFragment.context, clazz.java)
            this@MenuFragment.startActivity(i,
                    ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }
    }

}