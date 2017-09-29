package com.cxh.mvpart.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxh.mvpart.R;
import com.cxh.mvpart.di.component.DaggerFragmentComponent;
import com.cxh.mvpart.di.component.FragmentComponent;
import com.fingdo.statelayout.StateLayout;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/21
 */
public abstract class BaseFragment extends RxFragment implements  StateLayout.OnViewRefreshListener {

    private Unbinder unbinder;
    private StateLayout mStateLayout;
    protected FragmentComponent mFragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutID(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isUseEventBus()) EventBus.getDefault().register(this);

        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(getBaseActivity().mActivityComponent)
                .build();
        injectDagger();

        setupStateLayout(view);

        initViewsAndEvents();
    }

    private void setupStateLayout(View view) {
        mStateLayout = view.findViewById(R.id.stateLayout);
        if (mStateLayout == null) return;
        mStateLayout.setUseAnimation(false);
        mStateLayout.setTipText(5, getString(R.string.statelayout_loading));
        mStateLayout.setRefreshListener(this);
        mStateLayout.showLoadingView();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void showLoginView() {
        mStateLayout.showLoginView();
    }

    public void showContentView() {
        mStateLayout.showContentView();
    }

    public void showErrorView() {
        mStateLayout.showErrorView();
    }

    public void showTimeoutView() {
        mStateLayout.showTimeoutView();
    }

    @Override
    public void refreshClick() {
        mStateLayout.showLoadingView();
        refreshState();
    }

    @Override
    public void loginClick() {
    }

    @Subscribe
    public void onEvent(String event) {
        KLog.e();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isUnbind()) unbinder.unbind();

        if (isUseEventBus()) EventBus.getDefault().unregister(this);
    }

    public boolean isUnbind() {
        // Android中ViewPager + Fragment使用 ButterKnife注解时出现空指针NullPoint的情况
        return true;
    }

    protected boolean isUseEventBus() {
        return false;
    }

    protected abstract int getLayoutID();

    protected abstract void injectDagger();

    protected abstract void initViewsAndEvents();

    protected abstract void refreshState();

}
