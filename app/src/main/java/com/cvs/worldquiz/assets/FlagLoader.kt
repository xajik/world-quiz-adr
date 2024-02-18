package com.cvs.worldquiz.assets

import android.content.Context
import android.text.TextUtils
import android.util.Log

import com.facebook.drawee.view.SimpleDraweeView
import com.cvs.worldquiz.R

import java.io.File
import java.io.IOException
import java.util.Arrays

/**
 * @author IgorSteblii on 14.11.16.
 */

class FlagLoader(context: Context) {

    private val mHasFrags: Boolean
    //todo move this list to realm db in country table
    private val mFlags: Array<String>

    init {
        val mAssetManager = context.assets
        val list = mAssetManager.list(FLAGS_PATH)
        mFlags = arrayOf(*list)
        mHasFrags = mFlags.isNotEmpty()
    }

    fun setFlag(countryCode: String, imageView: SimpleDraweeView) {
        if (!mHasFrags) {
            Log.e(TAG, "flags are empty, suppose we failed to load assets")
            return
        }
        val path = getPath(countryCode)
        if (TextUtils.isEmpty(path)) {
            Log.w(TAG, "Missing flag for $countryCode")
            ImageLoader.load(imageView, R.drawable.baseline_flag_black_24)
        } else {
            ImageLoader.load(imageView, path!!)
        }
    }

    private fun getPath(countryCode: String): String? {
        var path: String? = null
        if (!TextUtils.isEmpty(countryCode)) {
            val flag = countryCode.toLowerCase() + RESOLUTION
            if (mFlags!!.contains(flag)) {
                path = ASSETS_PATH + FLAGS_PATH + File.separator + flag
            }
        }
        return path
    }

    companion object {

        private val TAG = FlagLoader::class.java.simpleName
        private const val ASSETS_PATH = "asset:///"
        private const val FLAGS_PATH = "flags"
        private const val RESOLUTION = ".png"
    }

}
