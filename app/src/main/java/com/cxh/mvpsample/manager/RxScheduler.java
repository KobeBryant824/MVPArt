package com.cxh.mvpsample.manager;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 在RxJava的使用过程中我们会频繁的调用subscribeOn()和observeOn(),通过Transformer结合
 *
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public final class RxScheduler {

    /**
     * @param activity 控制RxJava随Activity的生命周期
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> schedulersObservableTransformer(RxAppCompatActivity activity) {

        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle());
    }

    public static <T> FlowableTransformer<T, T> schedulersFlowableTransformer(RxAppCompatActivity activity) {

        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle());
    }


}
