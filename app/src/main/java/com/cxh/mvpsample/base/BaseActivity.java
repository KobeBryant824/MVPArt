package com.cxh.mvpsample.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cxh.mvpsample.MApplication;
import com.hss01248.pagestate.PageManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public PageManager mPageStateManager;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        MApplication.getContext().addPage(this);
        mUnbinder = ButterKnife.bind(this);

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
    }

    public abstract int getLayoutID();

    public abstract void RetryEvent();

}
