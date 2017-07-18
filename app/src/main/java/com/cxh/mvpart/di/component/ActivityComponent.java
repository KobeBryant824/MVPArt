package com.cxh.mvpart.di.component;

import android.app.Activity;
import android.content.Context;

import com.cxh.mvpart.di.qualifier.ContextLife;
import com.cxh.mvpart.di.moduel.ActivityModule;
import com.cxh.mvpart.di.scope.ActivityScoped;
import com.cxh.mvpart.ui.activity.UserActivity;
import com.cxh.mvpart.ui.activity.MainActivity;

import dagger.Component;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
@ActivityScoped
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getContext();

    Activity getActivity();

    void inject(MainActivity activity);

    void inject(UserActivity activity);

}
