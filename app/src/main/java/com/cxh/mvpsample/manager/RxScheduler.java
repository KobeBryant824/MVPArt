package com.cxh.mvpsample.manager;


import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public final class RxScheduler {

    /**
     * 在RxJava的使用过程中我们会频繁的调用subscribeOn()和observeOn(),通过Transformer结合
     * Observable.compose()我们可以复用这些代码
     *
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T,T> schedulersObservableTransformer() {

        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T,T> schedulersFlowableTransformer() {

        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
