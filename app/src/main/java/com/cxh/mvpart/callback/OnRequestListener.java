package com.cxh.mvpart.callback;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface OnRequestListener<T> {

    void onSuccess(T t);

    void onFailed();
}
