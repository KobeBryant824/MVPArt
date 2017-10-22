package com.cxh.mvpart.di

import android.content.Context
import com.cxh.mvpart.App
import com.cxh.mvpart.Constant.BASEURL
import com.cxh.mvpart.RestfulApi
import com.cxh.mvpart.manager.CacheInterceptor
import com.cxh.mvpart.util.SDCardUtils
import com.socks.library.KLog
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: App): Context

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideRestfulApi(retrofit: Retrofit): RestfulApi {
            return retrofit.create(RestfulApi::class.java)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideOkHttpClient(application: App): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor(KLog::e)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY //包含header、body数据

            return OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(CacheInterceptor())
                    .cache(getCache(application))
                    .build()
        }

        @JvmStatic
        private fun getCache(application: App): Cache {
            val cacheFile: File = if (SDCardUtils.isSDCardEnable())
                File(application.externalCacheDir!!.toString(), "cache")
            else
                File(application.cacheDir.toString(), "cache")
            val cacheSize = 10 * 1024 * 1024

            return Cache(cacheFile, cacheSize.toLong())
        }
    }

}