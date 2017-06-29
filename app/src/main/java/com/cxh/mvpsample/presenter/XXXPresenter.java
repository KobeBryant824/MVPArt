package com.cxh.mvpsample.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.cxh.mvpsample.base.BaseView;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.repository.XXXRepository;
import com.cxh.mvpsample.ui.activity.XXXActivity;

import javax.inject.Inject;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class XXXPresenter implements XXXContract.Presenter {

    private XXXRepository mXXXRepository;
    private XXXContract.View mView;
    private XXXActivity mXXXActivity;

    @Inject
    XXXPresenter(Activity activity) {
        mXXXActivity = (XXXActivity) activity;
        mXXXRepository = new XXXRepository();
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (XXXContract.View) view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mXXXRepository.requestData(mXXXActivity, new OnRequestListener<XXXApi.WelcomeEntity>() {

            @Override
            public void onSuccess(XXXApi.WelcomeEntity data) {
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
