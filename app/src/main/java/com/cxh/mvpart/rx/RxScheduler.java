package com.cxh.mvpart.rx;


import com.cxh.mvpart.rx.function.RetryWithDelay;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 在RxJava的使用过程中我们会频繁的调用subscribeOn()和observeOn(),通过Transformer结合
 *
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/21
 */
public final class RxScheduler {

    public static <T> ObservableTransformer<T, T> switchSchedulers(RxAppCompatActivity activity) {

        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(1, 3))
                .compose(activity.bindToLifecycle());
    }

}
