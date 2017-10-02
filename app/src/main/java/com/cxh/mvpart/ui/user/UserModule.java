package com.cxh.mvpart.ui.user;

import com.cxh.mvpart.di.ActivityScoped;
import com.cxh.mvpart.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/10/1
 */
@Module
public abstract class UserModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract UserFragment userFragment();

    @ActivityScoped
    @Binds
    abstract UserContract.Presenter userPresenter(UserPresenter presenter);


}
