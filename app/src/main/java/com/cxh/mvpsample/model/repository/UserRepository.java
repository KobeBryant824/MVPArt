package com.cxh.mvpsample.model.repository;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.callback.OnRequestListener;
import com.cxh.mvpsample.contract.UserContract;
import com.cxh.mvpsample.manager.RxScheduler;
import com.cxh.mvpsample.model.api.UserApi;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserRepository implements UserContract.Model {

    @Override
    public void requestData(RxAppCompatActivity activity, final OnRequestListener<UserApi.WelcomeEntity> listener) {

        UserApi userApi = App.getRetrofit().create(UserApi.class);

        userApi.welcomeObservalbe()
                .compose(RxScheduler.schedulersObservableTransformer(activity))
                .subscribe(listener::onSuccess, throwable -> listener.onFailed());

        Flowable.interval(1, TimeUnit.SECONDS)
                .doOnCancel(() -> KLog.e("Unsubscribing subscription from onCreate()"))
                .compose(activity.bindToLifecycle())
                .subscribe(aLong -> KLog.e("Started in onCreate(), running until onDestroy(): " + aLong));

    }

}
