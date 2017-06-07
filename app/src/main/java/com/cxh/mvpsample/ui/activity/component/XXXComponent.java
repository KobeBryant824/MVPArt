package com.cxh.mvpsample.ui.activity.component;

import com.cxh.mvpsample.ui.activity.XXXActivity;
import com.cxh.mvpsample.ui.activity.moduel.XXXModuel;

import dagger.Component;

/**
 * Desc:
 * Created by Hai (haigod7@gmail.com) on 2017/6/7 13:46.
 */
@Component(modules = XXXModuel.class)
public interface XXXComponent {

    void inject(XXXActivity xxxActivity);
}
