package com.cxh.mvpsample.contract;


import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.base.IView;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.presenter.XXXPresenter;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface XXXContract {

    interface View extends IView<XXXPresenter> {

        void setData(XXXApi.WelcomeEntity data);

        void showMessage(String message);

    }

    interface Presenter extends IPresenter {

        void loadData();
    }

}
