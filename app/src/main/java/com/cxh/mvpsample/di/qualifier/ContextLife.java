package com.cxh.mvpsample.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 控制context的生命周期
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {

    String value() default "Application";
}
