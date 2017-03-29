package com.cxh.mvpsample.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/16 11:23.
 */
public abstract class BaseFragment extends RxFragment {
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutID(), null);
        mUnbinder = ButterKnife.bind(view);
        return view;
    }

    public abstract int getLayoutID();

}
