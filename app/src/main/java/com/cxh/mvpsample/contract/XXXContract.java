package com.cxh.mvpsample.contract;

/**
 * Model Layer: 负责检索、存储、操作数据，包括来自网络、数据库、磁盘文件和SharedPreferences的数据；
 * View Layer: 只负责UI的绘制呈现，包含Fragment和一些自定义的UI组件，View层需要实现ViewInterface接口。
 *              Activity在项目中不再负责View的职责，仅仅是一个全局的控制者，负责创建View和Presenter的实例；
 * Presenter Layer: 作为View Layer和Module Layer的之间的纽带，它从model层中获取数据，然后调用View的接口去控制View；
 * Contract: 我们参照Google的demo加入契约类Contract来统一管理View和Presenter的接口，使得某一功能模块的接口能更加直观的呈现出来，这样做是有利于后期维护的。
 */
import com.cxh.mvpsample.base.BasePresenter;
import com.cxh.mvpsample.base.BaseView;
import com.cxh.mvpsample.model.entity.WelcomeEntity;

/**
 * 更直观的看到View中的方法和Presenter中的方法的关联，XXX具体指定，项目里可以有多个Contract
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public interface XXXContract {

    //interface View extends BaseView<T>  T可以是其他Presenter
    interface View extends BaseView<Presenter> {

        void showContent();

        void showError();

        void setData(WelcomeEntity data);//将网络请求得到的用户信息回调

        void showMessage(String message);

        String loadUserId();//假设接口请求需要一个userId
    }

    interface Presenter extends BasePresenter {

        void loadData();
    }

}
