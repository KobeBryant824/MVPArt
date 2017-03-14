package com.cxh.mvpsample.model.repository;

import com.cxh.mvpsample.model.OnRequestListener;
import com.cxh.mvpsample.model.service.RequestService;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.util.RetrofitUtil;
import com.socks.library.KLog;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 具体页面的数据请求
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXDataRepository implements RequestBiz {

    @Override
    public void requestForData(final OnRequestListener listener) {

        //这里采用的是Java的动态代理模式
        RequestService requestSerives = RetrofitUtil.getInstance().getRetrofit().create(RequestService.class);

        Observable<WelcomeEntity> observable = requestSerives.getByRxjava();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelcomeEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WelcomeEntity value) {
                        listener.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
