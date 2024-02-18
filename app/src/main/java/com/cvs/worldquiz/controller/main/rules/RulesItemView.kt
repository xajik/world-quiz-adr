package com.cvs.worldquiz.controller.main.rules

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.cvs.worldquiz.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class RulesItemView : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View =
            with(ui) {
                cardView {
                    lparams(matchParent, wrapContent) {
                        margin = resources.getDimensionPixelSize(R.dimen.card_base_margin)
                        minimumHeight = resources.getDimensionPixelSize(R.dimen.list_item_height)
                    }

                    background.setColorFilter(ContextCompat.getColor(context, R.color.grey_50), PorterDuff.Mode.SRC_ATOP)

                    verticalLayout {

                        // title
                        textView {
                            id = R.id.rules_title
                            background = ContextCompat.getDrawable(context, android.R.color.white)
                            gravity = Gravity.CENTER_VERTICAL
                            textAppearance = R.style.Button_Body2
                        }.lparams(width = matchParent) {
                            topMargin = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
                            bottomMargin = resources.getDimensionPixelSize(R.dimen.card_base_margin)
                            setPadding(dip(3), 0, 0, 0)
                        }

                        // subtitle
                        textView {
                            id = R.id.rules_sub_title
                            textAppearance = R.style.Button_Body1
                            gravity = Gravity.CENTER_VERTICAL
                        }.lparams(width = matchParent, height = wrapContent) {
                            leftMargin = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
                            topMargin = resources.getDimensionPixelSize(R.dimen.card_base_margin)
                            bottomMargin = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
                        }

                    }.lparams(width = matchParent, height = matchParent)
                }

            }

}