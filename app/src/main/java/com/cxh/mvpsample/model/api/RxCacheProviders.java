package com.cxh.mvpsample.model.api;


import com.cxh.mvpsample.model.entity.WelcomeEntity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/3
 */
public interface RxCacheProviders {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<WelcomeEntity> getUser(Observable<WelcomeEntity> observable, EvictProvider evictProvider);


}
