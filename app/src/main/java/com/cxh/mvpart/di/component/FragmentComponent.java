package com.cxh.mvpart.di.component;

import com.cxh.mvpart.di.scope.FragmentScoped;
import com.cxh.mvpart.ui.fragment.UserFragment;

import dagger.Component;

/**
 * Fragment容器，依赖于ActivityComponent
 * 负责为注入的Fragment提供对象，限定对应的对象的生命周期
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7
 */
@FragmentScoped
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {

    void inject(UserFragment fragment);

//    void inject(XXX2Fragment fragment);

}

