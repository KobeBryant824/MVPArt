package com.cxh.mvpsample.ui.activity;

import android.widget.FrameLayout;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseActivity;
import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.presenter.XXXPresenter;
import com.cxh.mvpsample.ui.fragment.XXXFragment;
import com.cxh.mvpsample.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class XXXActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout mContent;
    @Inject
    XXXPresenter mPresenter;
    @Inject
    XXXFragment mXXXFragment;

    @Override
    public int getLayoutID() {
        return R.layout.activity_xxxx;
    }

    @Override
    protected void initDagger() {
        mActivityComponent.inject(this);
    }

    @Override
    protected BasePresenter initPresenter() {
        mPresenter.attachView(mXXXFragment);
        return null; // 假如Activity有多个Fragment，让各自Fragment控制presenter
    }

    @Override
    protected void initViewsAndEvents() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mXXXFragment, R.id.content);
    }

}
