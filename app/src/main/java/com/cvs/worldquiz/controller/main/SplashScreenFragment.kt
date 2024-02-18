package com.cvs.worldquiz.controller.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cvs.worldquiz.R
import com.cvs.worldquiz.countries.CountriesLoader
import io.reactivex.disposables.CompositeDisposable

class SplashScreenFragment : BaseFragment() {

    private lateinit var countriesLoader: CountriesLoader
    private val disposables = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        countriesLoader = CountriesLoader()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        val disposable = countriesLoader
                .loaded()
                .subscribe { loaded ->
                    if (loaded) {
                        owner.onLaunchScreenPassed()
                    } else {
                        load()
                    }
                }
        disposables.add(disposable)
    }

    private fun load() {
        val disposable = countriesLoader
                .load(context)
                .subscribe { loaded ->
                    if (loaded) {
                        owner.onLaunchScreenPassed()
                    } else {
                        Toast.makeText(context, "Error loading data", Toast.LENGTH_LONG).show()
                        activity?.finish()
                    }
                }
        disposables.add(disposable)
    }

    override fun onPause() {
        super.onPause()
        disposables.dispose()
    }

}