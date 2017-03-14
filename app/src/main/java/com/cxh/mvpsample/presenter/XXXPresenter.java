package com.cxh.mvpsample.presenter;

import android.os.Handler;
import android.os.Looper;

import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.model.OnRequestListener;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.model.repository.RequestBiz;
import com.cxh.mvpsample.model.repository.XXXDataRepository;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXPresenter implements XXXContract.Presenter {
    private XXXContract.View view;
    private RequestBiz mRequestBiz;
    private Handler mHandler;

    public XXXPresenter(XXXContract.View view) {
        this.view = view;
        view.setPresenter(this);
        mRequestBiz = new XXXDataRepository();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadData() {
        mRequestBiz.requestForData(new OnRequestListener<WelcomeEntity>() {

            @Override
            public void onSuccess(final WelcomeEntity data) {
                view.showContent();
                view.setData(data);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //显示Toast
                    }
                });
            }

            @Override
            public void onFailed() {
                view.showError();

            }
        });
    }

    @Override
    public void start() {
        loadData();
    }

}
