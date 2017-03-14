package com.cxh.mvpsample.model;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public interface OnRequestListener<T> {

    void onSuccess(T t);

    void onFailed();
}
