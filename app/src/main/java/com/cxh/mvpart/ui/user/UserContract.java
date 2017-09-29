package com.cxh.mvpart.ui.user;


import com.cxh.mvpart.base.IPresenter;
import com.cxh.mvpart.base.IView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public interface UserContract {

    interface View extends IView<UserPresenter> {

        void setData(String data);

    }

    interface Presenter extends IPresenter<UserFragment> {

    }

}
