package com.cxh.mvpsample.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
