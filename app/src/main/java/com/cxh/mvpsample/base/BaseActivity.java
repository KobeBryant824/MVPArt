package com.cxh.mvpsample.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cxh.mvpsample.manager.ActivityManager;
import com.cxh.mvpsample.model.api.entity.Event;
import com.hss01248.pagestate.PageManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Desc:
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51 13:56.
 */
public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    protected PageManager mPageStateManager;
    private Unbinder mUnbinder;
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        mUnbinder = ButterKnife.bind(this);
        ActivityManager.getInstance().pushOneActivity(this);

        if (useEventBus())
            EventBus.getDefault().register(this);

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        PageManager.initInApp(getApplicationContext());
        mPageStateManager = PageManager.init(this, true, this::RetryEvent);
        mPageStateManager.showLoading();

        mPresenter = initPresenter();
        initViewsAndEvents();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        // 其他用得少请自行引用 autolayoutwidget 库
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }
        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }
        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.unSubscribe();
        super.onDestroy();
        mUnbinder.unbind();
        ActivityManager.getInstance().popOneActivity(this);
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MainEvent(Event event) {
    }

    public boolean useEventBus() {
        return true;
    }

    protected void getBundleExtras(Bundle extras) {
    }

    protected abstract int getLayoutID();

    protected abstract T initPresenter();

    protected abstract void initViewsAndEvents();

    protected abstract void RetryEvent();


}
