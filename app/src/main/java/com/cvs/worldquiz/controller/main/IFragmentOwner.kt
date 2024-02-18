package com.cvs.worldquiz.controller.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.cvs.worldquiz.R
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface IFragmentOwner {

    fun getSupportFragmentManager(): FragmentManager

}

fun IFragmentOwner.addFragment(fragment: Fragment) {
    getSupportFragmentManager()
            .beginTransaction()
            .addToBackStack(fragment::class.simpleName)
            .add(R.id.fragment_container, fragment, fragment::class.simpleName)
            .commit()
}

fun IFragmentOwner.switchFragment(clazz: KClass<out Fragment>) {
    val fragment = clazz.createInstance()
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment::class.simpleName)
            .commit()
}

fun IFragmentOwner.addFragment(clazz: KClass<out Fragment>) {
    val fragmentByTag = getSupportFragmentManager()
            .findFragmentByTag(clazz.simpleName)
    fragmentByTag?.let {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(it::class.simpleName)
                .show(it)
                .commit()
        return
    }

    val fragment = clazz.createInstance()
    getSupportFragmentManager()
            .beginTransaction()
            .addToBackStack(fragment::class.simpleName)
            .add(R.id.fragment_container, fragment, fragment::class.simpleName)
            .commit()
}