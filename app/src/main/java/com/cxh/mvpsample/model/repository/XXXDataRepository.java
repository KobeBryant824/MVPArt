package com.cxh.mvpsample.model.repository;

import android.util.Log;

import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.manager.RxDisposable;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.util.RetrofitUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * +
 * 具体页面的数据请求
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXDataRepository implements RequestBiz {

    @Override
    public void requestData(final OnRequestListener listener) {

        // 这里采用的是Java的动态代理模式
        XXXApi requestSerives = RetrofitUtils.getInstance().create(XXXApi.class);

        Disposable subscribe = requestSerives.getWelcomeEntity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(welcomeEntity -> listener.onSuccess(welcomeEntity),
                        throwable -> listener.onFailed(),
                        () -> {
                });

        Disposable subscribe1 = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnCancel(() -> Log.e("hh", "Unsubscribing subscription from onCreate()"))
                .subscribe(aLong -> Log.e("hh", "Started in onCreate(), running until onDestroy(): " + aLong));

        RxDisposable.add(subscribe);

        RxDisposable.add(subscribe1);

    }

}
