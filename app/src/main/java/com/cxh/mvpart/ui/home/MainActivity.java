package com.cxh.mvpart.ui.home;

import android.widget.Button;
import android.widget.Toast;

import com.cxh.mvpart.App;
import com.cxh.mvpart.R;
import com.cxh.mvpart.base.BaseActivity;
import com.cxh.mvpart.base.IPresenter;
import com.cxh.mvpart.manager.ActivityManager;
import com.cxh.mvpart.ui.user.UserActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import io.reactivex.Observable;

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
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDagger() {

    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void refreshState() {

    }

    @Override
    protected void initViewsAndEvents() {
        toolbarTitle.setText(R.string.app_name);

        mvpBtn.setOnClickListener(v -> pushPage(UserActivity.class));

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

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(aLong -> mDoubleBackToExitPressedOnce = false);

    }

}
