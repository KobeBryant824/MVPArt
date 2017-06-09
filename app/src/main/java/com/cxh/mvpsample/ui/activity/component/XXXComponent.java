package com.cxh.mvpsample.ui.activity.component;

import com.cxh.mvpsample.ui.activity.XXXActivity;
import com.cxh.mvpsample.ui.activity.moduel.XXXModuel;

import dagger.Component;


/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Component(modules = XXXModuel.class)
public interface XXXComponent {

    void inject(XXXActivity xxxActivity);
}
