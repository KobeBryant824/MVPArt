package com.cxh.mvpsample.ui.activity;

import android.widget.Button;
import android.widget.Toast;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.manager.ActivityManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class MainActivity extends BaseActivity {

    @BindString(R.string.app_name)
    String mAppName;
    @BindView(R.id.mvp_btn)
    Button mvpBtn;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDagger() {

    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(aLong -> mPageStateManager.showContent());

        RxView.clicks(mvpBtn)
                .throttleFirst(2000, TimeUnit.MICROSECONDS)
                .subscribe(o -> pushPage(UserActivity.class));

        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> KLog.e("Unsubscribing subscription from onCreate()"))
                .compose(bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(num -> KLog.e("Started in onCreate(), running until onPause(): " + num));
    }

    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            super.onBackPressed();
            ActivityManager.getInstance().appExit();
            return;
        }

        mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再次点击退出" + mAppName, Toast.LENGTH_SHORT).show();

          Disposable subscribe = Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(aLong -> mDoubleBackToExitPressedOnce = false);

    }

}
