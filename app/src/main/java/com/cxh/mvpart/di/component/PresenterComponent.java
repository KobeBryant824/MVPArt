package com.cxh.mvpart.di.component;

import com.cxh.mvpart.di.moduel.AppModule;
import com.cxh.mvpart.ui.user.UserPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/9/28
 */
@Singleton
@Component(modules = {AppModule.class})
public interface PresenterComponent {

    void inject(UserPresenter userPresenter);
}
