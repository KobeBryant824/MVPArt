package com.cxh.mvpsample.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.cxh.mvpsample.MApplication;
import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 简单的页面还是用mvc（欢迎页、关于页、无数据请求、以后版本无更新页面~~）
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
    public void RetryEvent() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 解决Handler可能造成的内存泄漏，其实这段代码用handler也不会造成泄漏，hiahia~~
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mPageStateManager.showContent();
                    }
                });
    }

    @OnClick(R.id.mvp_btn)
    public void onClick() {
        startActivity(new Intent(this, XXXActivity.class));
    }

    boolean doubleBackToExitPressedOnce = false;

    // 双击返回键退出
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            MApplication.getContext().exitApp();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再次点击退出" + mAppName, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
