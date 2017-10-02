package com.cxh.mvpart.di;


import com.cxh.mvpart.ui.user.UserActivity;
import com.cxh.mvpart.ui.user.UserModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module()
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = UserModule.class)
    abstract UserActivity contributeUserActivityInjector();




}
