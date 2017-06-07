package com.cxh.mvpsample.presenter;

import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.repository.XXXRepository;

import javax.inject.Inject;

/**
 * 具体页面的数据处理
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class XXXPresenter extends BasePresenter<XXXContract.View> implements XXXContract.Presenter {
    private XXXRepository mXXXRepository;

    // 给P层注入V，V又注入P（V 需要 P的数据处理操作，P需要 V的视图更新）
    @Inject
    XXXPresenter(XXXContract.View view) {
        super(view);
        view.setPresenter(this);
        mXXXRepository = new XXXRepository();
    }

    @Override
    protected void start() {
        loadData();
    }

    @Override
    public void loadData() {
        mXXXRepository.requestData(new OnRequestListener<XXXApi.WelcomeEntity>() {

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
