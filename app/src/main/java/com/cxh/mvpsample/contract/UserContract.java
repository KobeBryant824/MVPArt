package com.cxh.mvpsample.contract;


import com.cxh.mvpsample.base.IModel;
import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.base.IView;
import com.cxh.mvpsample.model.entity.WelcomeEntity;
import com.cxh.mvpsample.presenter.UserPresenter;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface UserContract {

    interface View extends IView<UserPresenter> {

        void setData(WelcomeEntity welcomeEntity);
    }

    interface Presenter extends IPresenter<View> {

//        void checkUser();
    }

    interface Model extends IModel<WelcomeEntity> {

    }
}
