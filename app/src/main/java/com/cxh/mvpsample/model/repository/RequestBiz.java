package com.cxh.mvpsample.model.repository;

import com.cxh.mvpsample.listener.OnRequestListener;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public interface RequestBiz {

    void requestData(OnRequestListener listener);
}
