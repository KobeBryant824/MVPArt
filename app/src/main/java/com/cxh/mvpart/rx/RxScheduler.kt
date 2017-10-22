package com.cxh.mvpart.rx


import com.cxh.mvpart.rx.function.RetryWithDelay
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/21
 */
object RxScheduler {

    fun <T> switchSchedulers(activity: RxAppCompatActivity): ObservableTransformer<T, T> {

        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retryWhen(RetryWithDelay(1, 3))
                    .compose(activity.bindToLifecycle())
        }
    }

}
