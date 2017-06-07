package com.cxh.mvpsample.contract;


import com.cxh.mvpsample.base.IPresenter;
import com.cxh.mvpsample.base.IView;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.presenter.XXXPresenter;

/**
 * 更直观的看到 View中的方法和 Presenter中的方法的关联，一个页面对应一个 Contract
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public interface XXXContract {

    interface View extends IView<XXXPresenter> {

        void showContent();

        void showError();

        void setData(XXXApi.WelcomeEntity data); // 将网络请求得到的用户信息回调

        void showMessage(String message);

        String loadUserId(); // 假设接口请求需要一个 userId

    }

    interface Presenter extends IPresenter {

//        void login(String userName, String password); // 登录校验

        void loadData();
    }

}
