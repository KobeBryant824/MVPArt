package com.cxh.mvpsample.contract;


import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.base.BaseView;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.presenter.XXXPresenter;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface XXXContract {

    interface View extends BaseView<XXXPresenter> {

        void setData(XXXApi.WelcomeEntity data);
    }

    interface Presenter extends BasePresenter {

//        void checkUser();
    }

}
