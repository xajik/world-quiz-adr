package com.cvs.worldquiz.controller.main.discover

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.cvs.worldquiz.R
import com.cvs.worldquiz.controller.ext.slide.SlideFragment
import com.cvs.worldquiz.controller.main.BaseFragment
import com.cvs.worldquiz.db.Database
import com.cvs.worldquiz.db.model.Country
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoContext
import java.util.concurrent.TimeUnit

class DiscoverFragment : SlideFragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSearchInput: EditText
    private lateinit var disposable: Disposable


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = DiscoverFragmentView().createView(AnkoContext.create(context!!, this))
        setupUi(view)
        return view
    }

    private fun setupUi(view: View) {
        mSearchInput = view.findViewById(R.id.discover_search_input)
        mSearchInput.setHint(R.string.action_search)
        mRecyclerView = view.findViewById(R.id.discover_recycler)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAdapter = DiscoverAdapter(context!!)
        mAdapter.setDate(Database.shared.getAll(Country::class))
        mRecyclerView.adapter = mAdapter

        disposable = RxTextView
                .textChanges(mSearchInput)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mAdapter.filter(it)
                    mAdapter.notifyDataSetChanged()
                }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

}