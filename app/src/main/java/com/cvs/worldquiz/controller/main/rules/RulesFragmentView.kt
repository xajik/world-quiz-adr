package com.cvs.worldquiz.controller.main.rules

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.cvs.worldquiz.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class RulesFragmentView(private val listAdapter: RulesRecyclerAdapter) : AnkoComponent<Fragment> {

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {

            id = R.id.main_container
            lparams(width = matchParent, height = matchParent)

            frameLayout {

                id = R.id.content_container

                include<FrameLayout>(R.layout.layer_background) {
                }.lparams(width = matchParent, height = matchParent)

                recyclerView {
                    id = R.id.rules_recycler_view
                    layoutManager = LinearLayoutManager(context,
                            LinearLayoutManager.VERTICAL, false)
                    overScrollMode = View.OVER_SCROLL_NEVER
                    adapter = listAdapter
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}