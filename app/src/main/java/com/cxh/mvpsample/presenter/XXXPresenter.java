package com.cxh.mvpsample.presenter;

import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.manager.RxDisposable;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.repository.XXXDataRepository;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXPresenter implements XXXContract.Presenter {
    private XXXContract.View mView;
    private XXXDataRepository mRequestBiz;

    public XXXPresenter(XXXContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        mRequestBiz = new XXXDataRepository();
    }

    @Override
    public void loadData() {

        mRequestBiz.requestData(new OnRequestListener<XXXApi.WelcomeEntity>() {

            @Override
            public void onSuccess(final XXXApi.WelcomeEntity data) {
                mView.showContent();
                mView.setData(data);
            }

            @Override
            public void onFailed() {
                mView.showError();
            }
        });
    }

    @Override
    public void subscribe() {
        loadData();
    }

    @Override
    public void unSubscribe() {
        RxDisposable.clear();

        if (mView != null) mView = null;
    }

}
