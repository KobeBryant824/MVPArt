package com.cxh.mvpart.ui.user

import com.cxh.mvpart.di.ActivityScoped
import com.cxh.mvpart.di.FragmentScoped

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/10/1
 */
@Module
abstract class UserModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun userFragment(): UserFragment

    @ActivityScoped
    @Binds
    abstract fun userPresenter(presenter: UserPresenter): UserContract.Presenter


}
