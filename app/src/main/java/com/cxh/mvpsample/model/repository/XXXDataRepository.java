package com.cxh.mvpsample.model.repository;

import android.util.Log;

import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.manager.RxDisposable;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.util.RetrofitUtils;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 具体页面的数据请求
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXDataRepository implements RequestBiz {

    @Override
    public void requestForData(final OnRequestListener listener) {

        //这里采用的是Java的动态代理模式
        XXXApi requestSerives = RetrofitUtils.getInstance().create(XXXApi.class);

        Disposable subscribe = requestSerives.getFlowableByRxjava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WelcomeEntity>() {
                    @Override
                    public void accept(@NonNull WelcomeEntity welcomeEntity) throws Exception {
                        listener.onSuccess(welcomeEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        KLog.e();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        KLog.e();
                    }
                });

        Disposable subscribe1 = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("hh", "Unsubscribing subscription from onCreate()");
                    }
                })
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e("hh", "Started in onCreate(), running until onDestroy(): " + aLong);
                    }
                });

        RxDisposable.add(subscribe);

        RxDisposable.add(subscribe1);

//        requestSerives.getDataByRxjava()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<WelcomeEntity>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(WelcomeEntity welcomeEntity) {
//                        listener.onSuccess(welcomeEntity);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }


}
