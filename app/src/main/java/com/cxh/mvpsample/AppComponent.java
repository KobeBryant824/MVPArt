package com.cxh.mvpsample;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Desc:
 * Created by Hai (haigod7@gmail.com) on 2017/6/7 15:43.
 */
@Singleton
@Component(modules = AppModuel.class)
public interface AppComponent {

    App getApplication();

    Context getContext();
}
