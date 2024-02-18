package com.cvs.worldquiz.controller.main.rules

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.ext.slide.SlideFragment
import com.cvs.worldquiz.controller.main.BaseFragment
import org.jetbrains.anko.*

class RulesFragment : SlideFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adapter = RulesRecyclerAdapter(getData())
        return RulesFragmentView(adapter).createView(AnkoContext.create(context!!, this))
    }

    private fun getData(): Array<Array<String>> {
        return arrayOf(
                context!!.resources.getStringArray(R.array.rules_title),
                context!!.resources.getStringArray(R.array.rules_content))
    }

}