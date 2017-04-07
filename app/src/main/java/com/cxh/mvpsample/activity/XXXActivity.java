package com.cxh.mvpsample.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.transition.Explode;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseAutoActivity;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.presenter.XXXPresenter;
import com.socks.library.KLog;

import butterknife.BindView;


/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXActivity extends BaseAutoActivity implements XXXContract.View {
    private static final String ACTION_CODE = "ilovekobebryant";
    private XXXContract.Presenter mPresenter;

    @BindView(R.id.first_tv)
    TextView firstTv;
    @BindView(R.id.second_tv)
    AppCompatTextView secondTv;
    @BindView(R.id.third_cb)
    CheckBox thirdCb;
    @BindView(R.id.four_cb)
    AppCompatCheckBox fourCb;
    @BindView(R.id.rootlayout)
    RelativeLayout rootlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new XXXPresenter(this);
//        mPresenter.start();
    }

    @Override
    public void setPresenter(XXXContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initViewsAndEvents() {
        enterTransition();
        String action = getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            showSnackbar(rootlayout, "带参数的shortcuts");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void enterTransition() {
        // Set the animations.爆炸效果，还有slide滑动效果、fade渐变
        Explode explode = new Explode();
        explode.setDuration(500);
        explode.setInterpolator(new AccelerateDecelerateInterpolator());
        getWindow().setEnterTransition(explode);
    }

    @Override
    public void setData(WelcomeEntity data) {
        KLog.d(data);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String loadUserId() {
        return null;
    }

    @Override
    public void showContent() {
        mPageStateManager.showContent();
    }

    @Override
    public void showError() {
        mPageStateManager.showError();
    }

    @Override
    public void RetryEvent() {
        mPageStateManager.showLoading();
        mPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
