package com.cxh.mvpart.ui.widget

import android.graphics.*

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

import java.security.MessageDigest

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/3
 */
class GlideCircleTransformation : BitmapTransformation() {

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
        return circleCrop(pool, toTransform)
    }

    private fun circleCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
        if (source == null) return null

        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val square = Bitmap.createBitmap(source, x, y, size, size)
        val circle = pool.get(size, size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(circle)
        val paint = Paint()
        paint.shader = BitmapShader(square, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        return circle
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }
}
