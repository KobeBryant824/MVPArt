package com.cxh.mvpsample.ui.activity;

import android.widget.FrameLayout;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.presenter.UserPresenter;
import com.cxh.mvpsample.ui.fragment.UserFragment;
import com.cxh.mvpsample.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout mContent;
    @Inject
    UserPresenter mPresenter;
    @Inject
    UserFragment mUserFragment;

    @Override
    public int getLayoutID() {
        return R.layout.activity_user;
    }

    @Override
    protected void initDagger() {
        mActivityComponent.inject(this);
    }

    @Override
    protected IPresenter initPresenter() {
        mPresenter.attachView(mUserFragment);
        return null; // 假如Activity有多个Fragment，让各自Fragment控制presenter
    }

    @Override
    protected void initDataAndEvent() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mUserFragment, R.id.content);
    }

}
