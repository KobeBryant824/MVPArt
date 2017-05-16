package com.cxh.mvpsample.base;

import com.cxh.mvpsample.manager.RxDisposable;

/**
 * Desc: 所有 IPresenter的实现类请继承 BasePresenter
 * Created by Hai (haigod7@gmail.com) on 2017/5/16 10:40.
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
        // 注销 V层引用、手动释放后台请求，避免造成内存泄漏（当然用 rxlifecycle2就可以很好解决但是只能在 RxActivity中、RxFragment调用，或者 M层持有 context主动关联）
        RxDisposable.clear();

        if (mView != null) mView = null;
    }

    protected abstract void start();

}
