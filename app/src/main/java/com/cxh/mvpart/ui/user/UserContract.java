package com.cxh.mvpart.ui.user;


import com.cxh.mvpart.base.IPresenter;
import com.cxh.mvpart.base.IView;
import com.cxh.mvpart.ui.user.UserPresenter;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface UserContract {

    interface View extends IView<UserPresenter> {

        void setData(String jsonObject);
    }

    interface Presenter extends IPresenter<UserFragment> {

//        void checkUser();
    }

}
