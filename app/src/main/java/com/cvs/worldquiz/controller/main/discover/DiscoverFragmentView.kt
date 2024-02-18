package com.cvs.worldquiz.controller.main.discover

import android.graphics.PorterDuff
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.cvs.worldquiz.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DiscoverFragmentView : AnkoComponent<Fragment> {

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        frameLayout {

            id = R.id.main_container
            lparams(width = matchParent, height = matchParent)

            frameLayout {

                id = R.id.content_container

                constraintLayout {

                    id = R.id.discover_holder

                    val backgroundView = include<FrameLayout>(R.layout.layer_background) {

                    }.lparams(width = matchParent, height = matchParent)


                    val cardView = cardView {

                        id = R.id.discover_search
                        elevation = 3f

                        background.setColorFilter(ContextCompat.getColor(context, R.color.grey_50), PorterDuff.Mode.SRC_ATOP)

                        editText {
                            id = R.id.discover_search_input
                        }.lparams(width = matchParent, height = matchParent)
                    }.lparams(width = matchParent, height = wrapContent) {
                        margin = resources.getDimensionPixelSize(R.dimen.card_base_margin)
                        padding = resources.getDimensionPixelSize(R.dimen.card_base_margin)
                    }

                    val listView = recyclerView {
                        id = R.id.discover_recycler
                        layoutManager = LinearLayoutManager(context)
                    }.lparams(width = 0, height = 0)

                    applyConstraintSet {

                        backgroundView {
                            connect(
                                    ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.TOP of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.BOTTOM to ConstraintSetBuilder.Side.BOTTOM of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintSet.PARENT_ID
                            )
                        }

                        cardView {
                            connect(
                                    ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.TOP of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintSet.PARENT_ID
                            )
                        }

                        listView {
                            connect(
                                    ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.BOTTOM of cardView,
                                    ConstraintSetBuilder.Side.BOTTOM to ConstraintSetBuilder.Side.BOTTOM of ConstraintSet.PARENT_ID,
                                    ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintSet.PARENT_ID
                            )
                        }

                    }

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = matchParent, height = matchParent)
        }
    }
}