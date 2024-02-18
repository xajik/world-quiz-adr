package com.cvs.worldquiz.assets.fresco

import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor
import com.facebook.imagepipeline.request.ImageRequestBuilder

fun SimpleDraweeView.blur(resId: Int) {
    val postProcessor = IterativeBoxBlurPostProcessor(5)
    val request = ImageRequestBuilder.newBuilderWithSource(UriUtil.getUriForResourceId(resId))
            .setPostprocessor(postProcessor)
            .build()
    val newController = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(controller)
            .build()
    controller = newController
}