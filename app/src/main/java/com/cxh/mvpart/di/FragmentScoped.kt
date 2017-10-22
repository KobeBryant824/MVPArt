package com.cxh.mvpart.di

import javax.inject.Scope

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
@Scope
@kotlin.annotation.Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped
