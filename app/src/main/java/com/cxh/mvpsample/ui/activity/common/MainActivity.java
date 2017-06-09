package com.cxh.mvpsample.ui.activity.common;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.manager.ActivityManager;
import com.cxh.mvpsample.util.ToastUtils;
import com.cxh.mvpsample.ui.activity.XXXActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@RuntimePermissions
public class MainActivity extends BaseActivity {

    @BindString(R.string.app_name)
    String mAppName;

    @BindView(R.id.mvp_btn)
    Button mvpBtn;
    @BindView(R.id.permission_btn)
    Button permissionBtn;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    public void RetryEvent() {

    }

    @Override
    protected void initViewsAndEvents() {

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                // Replace or Expand lambda , alt + enter
                .subscribe(aLong -> mPageStateManager.showContent());

        RxView.clicks(mvpBtn)
                .throttleFirst(2000, TimeUnit.MICROSECONDS)
                .subscribe(o -> startActivity(new Intent(MainActivity.this, XXXActivity.class)));

        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> KLog.e("Unsubscribing subscription from onCreate()"))
                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(num -> KLog.e("Started in onCreate(), running until onPause(): " + num));
    }

    @OnClick({R.id.permission_btn})
    public void onClick(View view) {
        requestPermission();
    }

    private void requestPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MainActivityPermissionsDispatcher.agreeWithCheck(this);
        }
    }

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            ActivityManager.getInstance().appExit();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再次点击退出" + mAppName, Toast.LENGTH_SHORT).show();

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aLong -> doubleBackToExitPressedOnce = false);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void agree() {
        ToastUtils.show("写SD卡限权已申请");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("这是申请写SD卡权限的说明....")
                .setPositiveButton("确定", (dialog, which) -> {
                    // 再次执行请求
                    request.proceed();
                })
                .setNegativeButton("取消", (dialogInterface, i) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void permissionDenied() {
        ToastUtils.show("权限被拒绝");
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void neverAskAgain() {
        ToastUtils.show("下次需要该权限请到系统设置中打开");
    }
}
