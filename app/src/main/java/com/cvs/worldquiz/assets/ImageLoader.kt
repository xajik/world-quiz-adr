package com.cvs.worldquiz.assets

import android.net.Uri
import android.support.annotation.DrawableRes


import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.BasePostprocessor
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * @author IgorSteblii on 19.03.17.
 */

object ImageLoader {

    fun load(view: SimpleDraweeView, @DrawableRes resId: Int) {
        val uri = UriUtil.getUriForResourceId(resId)
        view.setImageURI(uri)
    }

    fun load(view: SimpleDraweeView, url: String) {
        view.setImageURI(url)
    }

}
