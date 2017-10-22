package com.cxh.mvpart.rx.function


import com.cxh.mvpart.rx.exception.ExceptionEngine

import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/30
 */
class HttpResultFunc<T> : Function<Throwable, Observable<T>> {

    @Throws(Exception::class)
    override fun apply(@NonNull throwable: Throwable): Observable<T> {
        return Observable.error(ExceptionEngine.handleException(throwable))
    }
}
