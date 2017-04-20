package com.cxh.mvpsample.view.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseAutoActivity;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.model.api.XXXApi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new XXXPresenter(this); // 给P层注入V，V又注入P，双向持有（V需要P的数据处理操作，P需要V的视图更新）
//        mPresenter.subscribe();
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
        return R.layout.activity_xxx;
    }

    @Override
    protected void initViewsAndEvents() {

        String action = getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            showSnackbar(firstTv, "带参数的shortcuts");
        }
    }

    @Override
    public void setData(XXXApi.WelcomeEntity data) {
        KLog.d(data);
        firstTv.setText(data.toString());
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
        mPresenter.unSubscribe();
        super.onDestroy();
    }
}
