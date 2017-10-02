package com.cxh.mvpart.di;

import com.cxh.mvpart.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/7 15:43
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class
})
interface AppComponent extends AndroidInjector<App>{

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}

}
