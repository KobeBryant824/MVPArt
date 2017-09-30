package com.cxh.mvpart.di.component;

import com.cxh.mvpart.ui.user.UserPresenter;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/9/28
 */
@Singleton
@Subcomponent
public interface PresenterComponent {

    void inject(UserPresenter userPresenter);
}
