package com.cxh.mvpsample.presenter;

import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.repository.XXXDataRepository;

/**
 * 具体页面的数据处理
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXPresenter extends BasePresenter<XXXContract.View> implements XXXContract.Presenter {
    private XXXDataRepository mXXXDataRepository;

    // 给P层注入V，V又注入P，双向持有（V需要P的数据处理操作，P需要V的视图更新）
    public XXXPresenter(XXXContract.View view) {
        super(view);
        view.setPresenter(this);
        mXXXDataRepository = new XXXDataRepository();
    }

    @Override
    protected void start() {
        loadData();
    }

    @Override
    public void loadData() {
        mXXXDataRepository.requestData(new OnRequestListener<XXXApi.WelcomeEntity>() {

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


}
