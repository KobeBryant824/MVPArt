package com.cxh.mvpart.di;

import android.content.Context;

import com.cxh.mvpart.App;
import com.cxh.mvpart.RestfulApi;
import com.cxh.mvpart.manager.CacheInterceptor;
import com.cxh.mvpart.util.SDCardUtils;
import com.socks.library.KLog;

import java.io.File;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.cxh.mvpart.Constant.BASEURL;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7
 */
@Module
abstract class AppModule {

    @Binds
    abstract Context bindContext(App application);

    @Provides
    @Singleton
    static RestfulApi provideRestfulApi(Retrofit retrofit) {
        return retrofit.create(RestfulApi.class);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(App application) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(KLog::e);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); //包含header、body数据

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(getCache(application))
                .build();
    }

    private static Cache getCache(App application) {
        File cacheFile;
        if (SDCardUtils.isSDCardEnable())
            cacheFile = new File(application.getExternalCacheDir().toString(), "cache");
        else
            cacheFile = new File(application.getCacheDir().toString(), "cache");
        int cacheSize = 10 * 1024 * 1024;

        return new Cache(cacheFile, cacheSize);
    }

}
