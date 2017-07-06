package com.cxh.mvpsample.model.repository;


import com.cxh.mvpsample.model.api.RestApi;
import com.cxh.mvpsample.model.api.RxCacheProviders;
import com.cxh.mvpsample.model.entity.WelcomeEntity;

import java.io.File;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Retrofit;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7
 */
public class RxCacheClient {

    private final RxCacheProviders mRxCacheProviders;
    private final RestApi mRestApi;
    private static Retrofit mRetrofit;

    public static RxCacheClient init(File cacheDir, Retrofit retrofit) {
        mRetrofit = retrofit;
        return new RxCacheClient(cacheDir);
    }

    private RxCacheClient(File cacheDir) {
        mRxCacheProviders = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(RxCacheProviders.class);

        mRestApi = mRetrofit.create(RestApi.class);
    }

    public Observable<WelcomeEntity> getUser(boolean update) {
        return mRxCacheProviders.getUser(mRestApi.getUser(), new EvictProvider(update));
    }

}
