package com.cxh.mvpsample.di.moduel;

import android.app.Activity;
import android.content.Context;


import com.cxh.mvpsample.di.scope.ActivityScoped;
import com.cxh.mvpsample.di.qualifier.ContextLife;

import dagger.Module;
import dagger.Provides;

/**
 * 提供Activity和Context
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScoped
    @ContextLife("Activity")
    public Context provideContext() {
        return mActivity;
    }

    @Provides
    @ActivityScoped
    public Activity provideActivity() {
        return mActivity;
    }

}
