package com.cxh.mvpsample.model.repository;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.callback.OnRequestListener;
import com.cxh.mvpsample.contract.UserContract;
import com.cxh.mvpsample.manager.RxScheduler;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserRepository implements UserContract.Model {

    @Override
    public void requestData(RxAppCompatActivity activity, final OnRequestListener<WelcomeEntity> listener) {

        App.getRxCacheClient().getUser(false)
                .compose(RxScheduler.applyObservableSchedulers(activity))
                .subscribe(listener::onSuccess, throwable -> listener.onFailed());

    }

}
