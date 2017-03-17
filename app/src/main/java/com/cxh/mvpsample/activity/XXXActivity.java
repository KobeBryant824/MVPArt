package com.cxh.mvpsample.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.presenter.XXXPresenter;
import com.socks.library.KLog;


/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXActivity extends BaseActivity implements XXXContract.View {
    private XXXContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new XXXPresenter(this);
//        mPresenter.start();
    }

    @Override
    public void setPresenter(XXXContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }


    @Override
    public int getLayoutID() {
        return R.layout.activity_mvp;
    }

    @Override
    public void setData(WelcomeEntity data) {
        KLog.d(data);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String loadUserId() {
        return null;
    }

    @Override
    public void showLoading() {
        mPageStateManager.showLoading();
    }

    @Override
    public void showContent() {
        mPageStateManager.showContent();
    }

    @Override
    public void showError() {
        mPageStateManager.showError();
    }

    @Override
    public void RetryEvent() {
        mPageStateManager.showLoading();
        mPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
