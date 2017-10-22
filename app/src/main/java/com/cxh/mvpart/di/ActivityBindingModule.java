package com.cxh.mvpart.di;

import com.cxh.mvpart.ui.user.UserActivity;
import com.cxh.mvpart.ui.user.UserModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Module()
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = UserModule.class)
    abstract UserActivity contributeUserActivityInjector();


}
