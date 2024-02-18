package com.cvs.worldquiz.controller.main.discover

import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.ext.getString
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.custom.customView

class DiscoverItemView : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        cardView {

            lparams(matchParent, wrapContent) {
                margin = dip(3)
                minimumHeight = dip(72)
                padding = dip(3)
            }

            constraintLayout {

                val greyBackground = frameLayout {
                    id = R.id.discover_gray_background
                    background = ContextCompat.getDrawable(context, R.color.grey_800)
                }.lparams(matchParent, wrapContent) {
                    startToStart = PARENT_ID
                    endToEnd = PARENT_ID
                    topToBottom = PARENT_ID
                    bottomToBottom = PARENT_ID
                }

                /*Title*/
                val flagImage = customView<SimpleDraweeView> {
                    id = R.id.discover_country_image
                    hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_INSIDE
                }.lparams(dip(48), dip(48)) {
                    padding = dip(4)
                    topToTop = PARENT_ID
                    startToStart = PARENT_ID
                }

                val countryCode = textView {
                    id = R.id.discover_country_code
                    gravity = Gravity.CENTER
                    textAppearance = R.style.Button_Body2
                }.lparams(dip(48), dip(48)) {
                    endToEnd = PARENT_ID
                    topToTop = PARENT_ID
                }

                val countryName = textView {
                    id = R.id.discover_country_name
                    gravity = Gravity.CENTER
                    textAppearance = R.style.Button_Body2
                }.lparams(wrapContent, wrapContent) {
                    topToTop = PARENT_ID
                    startToEnd = R.id.discover_country_image
                    endToStart = R.id.discover_country_code
                }

                //Continent
                val continentTitle = textView {
                    id = R.id.discover_continent_title
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                    text = getString(R.string.continent)
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    topToBottom = R.id.discover_country_image
                    endToStart = R.id.discover_continent_value
                    startToStart = PARENT_ID
                }

                val continentValue = textView {
                    id = R.id.discover_continent_value
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    startToEnd = R.id.discover_continent_title
                    endToEnd = PARENT_ID
                    baselineToBaseline = R.id.discover_continent_title
                }

                //Capital
                val capitalTitle = textView {
                    id = R.id.discover_capital_title
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                    text = getString(R.string.capital)
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    topToBottom = R.id.discover_continent_title
                    endToStart = R.id.discover_capital_value
                    startToStart = PARENT_ID
                }

                val capitalValue = textView {
                    id = R.id.discover_capital_value
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    startToEnd = R.id.discover_capital_title
                    endToEnd = PARENT_ID
                    baselineToBaseline = R.id.discover_capital_title
                }

                //Population
                val populationTitle = textView {
                    id = R.id.discover_population_title
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                    text = getString(R.string.population)
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    topToBottom = R.id.discover_capital_title
                    endToStart = R.id.discover_population_value
                    startToStart = PARENT_ID
                }

                val populationValue = textView {
                    id = R.id.discover_population_value
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    startToEnd = R.id.discover_population_title
                    endToEnd = PARENT_ID
                    baselineToBaseline = R.id.discover_population_title
                }

                //Area
                val areaTitle = textView {
                    id = R.id.discover_area_title
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                    text = getString(R.string.area)
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    topToBottom = R.id.discover_population_title
                    endToStart = R.id.discover_area_value
                    startToStart = PARENT_ID
                }

                val areaValue = textView {
                    id = R.id.discover_area_value
                    gravity = Gravity.START
                    textAppearance = R.style.Button_Body1
                }.lparams(0, wrapContent) {
                    padding = dip(5)
                    startToEnd = R.id.discover_area_title
                    endToEnd = PARENT_ID
                    baselineToBaseline = R.id.discover_area_title
                }

            }.lparams(matchParent, matchParent)
        }
    }

}