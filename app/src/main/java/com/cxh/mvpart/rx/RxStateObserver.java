package com.cxh.mvpart.rx;


import com.cxh.mvpart.Constant;
import com.cxh.mvpart.rx.exception.ApiException;
import com.cxh.mvpart.rx.exception.ERROR;
import com.fingdo.statelayout.StateLayout;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/31
 */
public abstract class RxStateObserver<T> implements Observer<T> {
    private StateLayout mStateLayout;

    public RxStateObserver(StateLayout stateLayout) {
        mStateLayout = stateLayout;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        mStateLayout.showContentView();
        onNEXT(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
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
            switch (ex.getCode()) {
                case ERROR.ONNEXT_ERROR:
                    throw new RuntimeException(ex.getCause());
                case ERROR.CONNECT_ERROR:
                    mStateLayout.showNoNetworkView();
                    break;
                case ERROR.TIMEOUT_ERROR:
                    mStateLayout.showTimeoutView();
                    break;
                default:
                    mStateLayout.showErrorView();
                    break;
            }
        } else {
            switch (ex.getCode()) {
                case ERROR.TIMEOUT_ERROR:
                    mStateLayout.showTimeoutView();
                    break;
                case ERROR.CONNECT_ERROR:
                    mStateLayout.showNoNetworkView();
                    break;
                default:
                    mStateLayout.showErrorView();
                    break;
            }
        }
    }

    protected abstract void onNEXT(T t);

}


