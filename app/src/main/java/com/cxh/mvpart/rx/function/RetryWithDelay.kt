package com.cxh.mvpart.rx.function

import com.socks.library.KLog

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/8/18
 */
class RetryWithDelay(private val maxRetries: Int, private val retryDelaySecond: Int) : Function<Observable<Throwable>, ObservableSource<*>> {
    private var retryCount: Int = 0

    @Throws(Exception::class)
    override fun apply(@NonNull throwableObservable: Observable<Throwable>): ObservableSource<*> {

        return throwableObservable
                .flatMap(Function<Throwable, ObservableSource<*>> {
                    if (++retryCount <= maxRetries) {
                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                        KLog.e("get error, it will try after $retryDelaySecond second, retry count $retryCount")

                        return@Function Observable.timer(retryDelaySecond.toLong(), TimeUnit.SECONDS)
                    }
                    // Max retries hit. Just pass the error along.
                    Observable.error<Any>(it)
                })
    }
}