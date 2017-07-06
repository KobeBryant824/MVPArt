package com.cxh.mvpsample.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cxh.mvpsample.App;
import com.cxh.mvpsample.di.component.ActivityComponent;
import com.cxh.mvpsample.di.component.DaggerActivityComponent;
import com.cxh.mvpsample.di.moduel.ActivityModule;
import com.cxh.mvpsample.manager.ActivityManager;
import com.hss01248.pagestate.PageManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity {

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

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
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        // 其他用得少请自行引用 autolayoutwidget 库
        if (name.equals(LAYOUT_FRAMELAYOUT)) view = new AutoFrameLayout(context, attrs);

        if (name.equals(LAYOUT_LINEARLAYOUT)) view = new AutoLinearLayout(context, attrs);

        if (name.equals(LAYOUT_RELATIVELAYOUT)) view = new AutoRelativeLayout(context, attrs);

        return view == null ? super.onCreateView(name, context, attrs) : view;
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
