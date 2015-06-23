package com.wizmacau.androidrecipes.core;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by llitfkitfk on 6/23/15.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApp {
}
