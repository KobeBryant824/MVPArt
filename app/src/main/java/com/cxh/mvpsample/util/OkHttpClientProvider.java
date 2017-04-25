package com.cxh.mvpsample.util;

import android.content.Context;

import com.socks.library.KLog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 自定义OkHttp客户端
 * Created by Hai (haigod7@gmail.com) on 2017/3/13 14:21.
 */
public class OkHttpClientProvider {
    public static final long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000;
    public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private static volatile OkHttpClientProvider sInstance;
    private OkHttpClient mOkHttpClient;

    private OkHttpClientProvider() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(KLog::e);
        //包含header、body数据
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
//                .addNetworkInterceptor(new StethoInterceptor())
                //http数据log，日志中打印出HTTP请求&响应数据
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public static OkHttpClientProvider getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpClientProvider.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpClientProvider();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void setCache(Context appContext) {
        final File baseDir = appContext.getApplicationContext().getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            mOkHttpClient.newBuilder().cache((new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)));
        }
    }
}
