package com.cxh.mvpart.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cxh.mvpart.R
import com.cxh.mvpart.ui.widget.GlideCircleTransformation

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/29
 */
object GlideUtils {

    fun loadImage(path: Any, imageView: ImageView) {
        val context = imageView.context
        Glide.with(context)
                .load(path)
                .apply(requestOptions(R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                .into(imageView)
    }

    fun loadImage(path: Any, placeholderResId: Int, errorResId: Int, imageView: ImageView) {
        val context = imageView.context
        Glide.with(context)
                .load(path)
                .apply(requestOptions(placeholderResId, errorResId))
                .into(imageView)
    }

    fun loadCircleImage(path: Any, imageView: ImageView) {
        val context = imageView.context
        Glide.with(context)
                .load(path)
                .apply(circleRequestOptions(R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                .into(imageView)
    }

    fun loadCircleImage(path: Any, placeholderResId: Int, errorResId: Int, imageView: ImageView) {
        val context = imageView.context
        Glide.with(context)
                .load(path)
                .apply(circleRequestOptions(placeholderResId, errorResId))
                .into(imageView)
    }

    private fun requestOptions(placeholderResId: Int, errorResId: Int): RequestOptions {
        return RequestOptions()
                .placeholder(placeholderResId)
                .error(errorResId)
    }

    private fun circleRequestOptions(placeholderResId: Int, errorResId: Int): RequestOptions {
        return requestOptions(placeholderResId, errorResId)
                .transform(GlideCircleTransformation())
    }
}
