package com.cxh.mvpart.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.cxh.mvpart.App;
import com.cxh.mvpart.di.component.ActivityComponent;
import com.cxh.mvpart.di.component.DaggerActivityComponent;
import com.cxh.mvpart.di.moduel.ActivityModule;
import com.cxh.mvpart.manager.ActivityManager;
import com.hss01248.pagestate.PageManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity {

    protected ActivityComponent mActivityComponent;
    private Unbinder mUnbinder;
    public  PageManager mPageStateManager;
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        ActivityManager.getInstance().pushOneActivity(this);

        mUnbinder = ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (null != extras) getBundleExtras(extras);

        PageManager.initInApp(getApplicationContext());
        mPageStateManager = PageManager.init(this, true, this::RetryEvent);
        mPageStateManager.showLoading();

        mActivityComponent = DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build();
        initDagger();

        mPresenter = initPresenter();

        initViewsAndEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.start();
    }

    private void RetryEvent() {
        mPageStateManager.showLoading();
        if (mPresenter != null) mPresenter.start();
    }

    public void showContent() {
        mPageStateManager.showContent();
    }

    public void showError() {
        mPageStateManager.showError();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

        ActivityManager.getInstance().popOneActivity(this);
    }

    protected void pushPage(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void pushPage(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void pushPageThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    protected void pushPageThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    protected void pushPageForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void pushPageForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void showSnackbar(View v, String msg) {
        if (!TextUtils.isEmpty(msg)) Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }

    protected void getBundleExtras(Bundle extras) {
    }

    protected abstract @LayoutRes int getLayoutID();

    protected abstract void initDagger();

    protected abstract T initPresenter();

    protected abstract void initViewsAndEvents();

}
