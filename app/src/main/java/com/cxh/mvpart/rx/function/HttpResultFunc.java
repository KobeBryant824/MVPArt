package com.cxh.mvpart.rx.function;

import com.cxh.mvpart.rx.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/30
 */
public class HttpResultFunc<T>  implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
