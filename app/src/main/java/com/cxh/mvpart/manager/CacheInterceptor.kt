package com.cxh.mvpart.manager

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/9/29
 */
class CacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originResponse = chain.proceed(chain.request())

        //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
        return originResponse.newBuilder().removeHeader("pragma")
                .header("Cache-Control", "max-age=60").build()
    }

}