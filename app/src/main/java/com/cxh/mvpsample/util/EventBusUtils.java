package com.cxh.mvpsample.util;


import com.cxh.mvpsample.model.api.entity.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/5/5 17:34.
 */
public class EventBusUtils {

    public static void post(String tag){
        EventBus.getDefault().post(tag);
    }

    public static void post(Event event){
        EventBus.getDefault().post(event);
    }
}
