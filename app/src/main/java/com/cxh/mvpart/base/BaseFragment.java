package com.cxh.mvpart.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxh.mvpart.di.component.DaggerFragmentComponent;
import com.cxh.mvpart.di.component.FragmentComponent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/16
 */
public abstract class BaseFragment extends RxFragment {

    private Unbinder mUnbinder;
    private BaseActivity mActivity;
    protected FragmentComponent mFragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutID(), null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder().activityComponent(getBaseActivity().mActivityComponent).build();
        initDagger();
        initViewsAndEvents();
    }

    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected abstract int getLayoutID();

    protected abstract void initDagger();

    protected abstract void initViewsAndEvents();

}
