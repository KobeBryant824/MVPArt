package com.cxh.mvpart.contract;


import com.cxh.mvpart.base.IModel;
import com.cxh.mvpart.base.IPresenter;
import com.cxh.mvpart.base.IView;
import com.cxh.mvpart.model.entity.WelcomeEntity;
import com.cxh.mvpart.presenter.UserPresenter;

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
