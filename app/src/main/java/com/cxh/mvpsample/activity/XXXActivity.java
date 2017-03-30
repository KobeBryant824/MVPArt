package com.cxh.mvpsample.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
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

        String action = getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            showSnackbar(rootlayout, "带参数的shortcuts");
        }
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
    public void showLoading() {
        mPageStateManager.showLoading();
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
