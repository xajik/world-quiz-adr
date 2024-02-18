package com.cvs.worldquiz.controller.ext.slide

import android.support.v4.app.Fragment
import android.view.View
import com.cvs.worldquiz.R
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrInterface
import com.r0adkll.slidr.model.SlidrPosition

open class SlideFragment : Fragment() {

    private var slide: SlidrInterface? = null

    override fun onResume() {
        super.onResume()

        val container = view?.findViewById<View>(R.id.content_container)
        container?.let {
            slide = slide ?: Slidr.replace(it,
                    SlidrConfig.Builder().position(SlidrPosition.LEFT).build())
        }

    }

}