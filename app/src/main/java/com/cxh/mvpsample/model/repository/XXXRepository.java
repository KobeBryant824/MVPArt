package com.cxh.mvpsample.model.repository;

import android.util.Log;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.manager.RxDisposable;
import com.cxh.mvpsample.manager.RxScheduler;
import com.cxh.mvpsample.model.api.XXXApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class XXXRepository implements IRequestBiz<XXXApi.WelcomeEntity> {

    @Override
    public void requestData(final OnRequestListener<XXXApi.WelcomeEntity> listener) {

        XXXApi xxxApi =  App.getRetrofit().create(XXXApi.class);

        Disposable subscribe = xxxApi.getWelcomeEntity()
                .compose(RxScheduler.normalSchedulersTransformer())
                .subscribe(listener::onSuccess, throwable -> listener.onFailed(), () -> {});

        Disposable subscribe1 = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnCancel(() -> Log.e("hhh", "Unsubscribing subscription from onCreate()"))
                .subscribe(aLong -> Log.e("hhh", "Started in onCreate(), running until onDestroy(): " + aLong));

        RxDisposable.add(subscribe);

        RxDisposable.add(subscribe1);

    }

}
