package com.cxh.mvpsample.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.cxh.mvpsample.base.IView;
import com.cxh.mvpsample.contract.UserContract;
import com.cxh.mvpsample.callback.OnRequestListener;
import com.cxh.mvpsample.model.api.UserApi;
import com.cxh.mvpsample.model.repository.UserRepository;
import com.cxh.mvpsample.ui.activity.UserActivity;

import javax.inject.Inject;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserPresenter implements UserContract.Presenter {

    private UserRepository mUserRepository;
    private UserContract.View mView;
    private UserActivity mUserActivity;

    @Inject
    UserPresenter(Activity activity) {
        mUserActivity = (UserActivity) activity;
        mUserRepository = new UserRepository();
    }

    @Override
    public void attachView(@NonNull IView view) {
        mView = (UserContract.View) view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mUserRepository.requestData(mUserActivity, new OnRequestListener<UserApi.WelcomeEntity>() {

            @Override
            public void onSuccess(UserApi.WelcomeEntity data) {
                mView.showContent();
                mView.setData(data);
            }

            @Override
            public void onFailed() {
                mView.showError();
            }
        });
    }

}
