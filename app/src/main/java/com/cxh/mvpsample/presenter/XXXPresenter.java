package com.cxh.mvpsample.presenter;

import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.listener.OnRequestListener;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.model.repository.XXXRepository;

import javax.inject.Inject;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class XXXPresenter extends BasePresenter<XXXContract.View> implements XXXContract.Presenter {

    private XXXRepository mXXXRepository;

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
            public void onSuccess(XXXApi.WelcomeEntity data) {
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
