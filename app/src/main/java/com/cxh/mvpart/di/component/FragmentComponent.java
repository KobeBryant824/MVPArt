package com.cxh.mvpart.di.component;

import com.cxh.mvpart.di.scope.FragmentScoped;
import com.cxh.mvpart.ui.user.UserFragment;

import dagger.Component;

/**
 * Fragment容器，依赖于ActivityComponent
 * 负责为注入的Fragment提供对象，限制对应的对象的生命周期
 *
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7
 */
@FragmentScoped
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {


}

