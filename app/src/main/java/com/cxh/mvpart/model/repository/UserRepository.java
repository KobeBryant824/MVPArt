package com.cxh.mvpart.model.repository;

import com.cxh.mvpart.App;
import com.cxh.mvpart.callback.OnRequestListener;
import com.cxh.mvpart.contract.UserContract;
import com.cxh.mvpart.manager.RxScheduler;
import com.cxh.mvpart.model.entity.WelcomeEntity;
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
