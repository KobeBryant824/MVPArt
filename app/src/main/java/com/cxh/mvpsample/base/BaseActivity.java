package com.cxh.mvpsample.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.manager.ActivityManager;
import com.hss01248.pagestate.PageManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseActivity 无适配，还是用dp
 * BaseAutoActivity 多分辨率适配，用px，全局通用这种，有坑就单个页面extexds BaseActivity
 */

/**
 * 所有在activity中用到RxJava2都必须继承此BaseActivity（此框架未在activity请求数据）
 * ps：我用另外一种，把数据请求放到M层，让P层去控制RxJava的生命周期
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected PageManager mPageStateManager;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

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

        initViewsAndEvents();
    }

    protected void pushPageThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    protected void pushPageThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    protected void pushPageForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void pushPageForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void showSnackbar(View v, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        ActivityManager.getInstance().popOneActivity(this);
    }

    protected void getBundleExtras(Bundle extras) {
    }

    protected abstract int getLayoutID();

    protected abstract void RetryEvent();

    protected abstract void initViewsAndEvents();

}
