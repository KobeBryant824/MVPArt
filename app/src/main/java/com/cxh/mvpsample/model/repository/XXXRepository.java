package com.cxh.mvpsample.model.repository;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.manager.RxScheduler;
import com.cxh.mvpsample.model.api.XXXApi;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class XXXRepository implements IRequestBiz<XXXApi.WelcomeEntity> {

    @Override
    public void requestData(RxAppCompatActivity activity, final OnRequestListener<XXXApi.WelcomeEntity> listener) {

        XXXApi xxxApi = App.getRetrofit().create(XXXApi.class);

        xxxApi.welcomeObservalbe()
                .compose(RxScheduler.schedulersObservableTransformer(activity))
                .subscribe(listener::onSuccess, throwable -> listener.onFailed());

        Flowable.interval(1, TimeUnit.SECONDS)
                .doOnCancel(() -> KLog.e("Unsubscribing subscription from onCreate()"))
                .compose(activity.bindToLifecycle())
                .subscribe(aLong -> KLog.e("Started in onCreate(), running until onDestroy(): " + aLong));

    }

}
