package com.cxh.mvpsample.base;

import com.cxh.mvpsample.manager.RxDisposable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/5/16
 */
public abstract class BasePresenter<T extends IView> implements IPresenter {

    protected T mView;

    public BasePresenter(T view) {
        mView = view;
    }

    @Override
    public void subscribe() {
        start();
    }

    @Override
    public void unSubscribe() {
        RxDisposable.clear();

        if (mView != null) mView = null;
    }

    protected abstract void start();

}
