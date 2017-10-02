package com.cxh.mvpart.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxh.mvpart.R;
import com.fingdo.statelayout.StateLayout;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/21
 */
public abstract class BaseFragment extends RxFragment implements StateLayout.OnViewRefreshListener, HasSupportFragmentInjector {

    private Unbinder unbinder;
    private StateLayout mStateLayout;

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        if (isInject()) AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }

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

    protected boolean isInject() {
        return true;
    }

    public boolean isUnbind() {
        // Android中ViewPager + Fragment使用 ButterKnife注解时出现空指针NullPoint的情况
        return true;
    }

    protected boolean isUseEventBus() {
        return false;
    }

    protected abstract int getLayoutID();

    protected abstract void initViewsAndEvents();

    protected abstract void refreshState();

}
