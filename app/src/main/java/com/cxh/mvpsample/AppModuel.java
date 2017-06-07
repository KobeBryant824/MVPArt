package com.cxh.mvpsample;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Desc:
 * Created by Hai (haigod7@gmail.com) on 2017/6/7 15:39.
 */
@Module
public class AppModuel {
    private Context mContext;

    public AppModuel(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return (App) mContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

}
