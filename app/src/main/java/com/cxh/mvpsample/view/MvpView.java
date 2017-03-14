package com.cxh.mvpsample.view;

import com.cxh.mvpsample.base.BaseView;
import com.cxh.mvpsample.model.entity.WelcomeEntity;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public interface MvpView extends BaseView {

    void setData(WelcomeEntity data);

    void showMessage(String message);
}
