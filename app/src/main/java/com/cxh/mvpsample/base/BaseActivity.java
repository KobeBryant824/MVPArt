package com.cxh.mvpsample.base;

import android.os.Bundle;

import com.cxh.mvpsample.manager.ActivityManager;
import com.hss01248.pagestate.PageManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有用到RxJava2都必须继承此BaseActivity
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected PageManager mPageStateManager;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mUnbinder = ButterKnife.bind(this);
        ActivityManager.getInstance().pushOneActivity(this);

        PageManager.initInApp(getApplicationContext());
        mPageStateManager = PageManager.init(this, true, new Runnable() {
            @Override
            public void run() {
                RetryEvent();
            }
        });
        mPageStateManager.showLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        ActivityManager.getInstance().popOneActivity(this);
    }

    public abstract int getLayoutID();

    public abstract void RetryEvent();

}
