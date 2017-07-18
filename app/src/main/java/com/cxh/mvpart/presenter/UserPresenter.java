package com.cxh.mvpart.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.cxh.mvpart.callback.OnRequestListener;
import com.cxh.mvpart.contract.UserContract;
import com.cxh.mvpart.model.entity.WelcomeEntity;
import com.cxh.mvpart.model.repository.UserRepository;
import com.cxh.mvpart.ui.activity.UserActivity;

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
    public void attachView(@NonNull UserContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mUserRepository.requestData(mUserActivity, new OnRequestListener<WelcomeEntity>() {

            @Override
            public void onSuccess(WelcomeEntity welcomeEntity) {
                mView.showContent();
                mView.setData(welcomeEntity);
            }

            @Override
            public void onFailed() {
                mView.showError();
            }
        });
    }

}
