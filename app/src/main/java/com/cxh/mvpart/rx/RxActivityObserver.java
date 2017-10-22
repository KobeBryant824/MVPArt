package com.cxh.mvpart.rx;

import com.cxh.mvpart.Constant;
import com.cxh.mvpart.base.BaseActivity;
import com.cxh.mvpart.rx.exception.ApiException;
import com.cxh.mvpart.rx.exception.ERROR;
import com.cxh.mvpart.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/31
 */
public abstract class RxActivityObserver<T, K extends BaseActivity> implements Observer<T> {
    private K k;

    public RxActivityObserver() {}

    protected RxActivityObserver(K k) {
        this.k = k;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (null != k)
            k.showContentView();
        refreshUI(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (null != k)
            k.showErrorView();

        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            ApiException apiException = new ApiException(e, ERROR.ONNEXT_ERROR);
            apiException.setDisplayMessage("onNext()出错");
            onError(apiException);
        }
    }

    @Override
    public void onComplete() {

    }

    private void onError(ApiException ex) {
        if (Constant.BUILD) {
            if (ex.getCode() == ERROR.ONNEXT_ERROR)
                throw new RuntimeException(ex.getCause());
            else ToastUtils.show(ex.getDisplayMessage());
        } else {
            ToastUtils.show(ex.getDisplayMessage());
        }
    }

    protected abstract void refreshUI(T t);

}


