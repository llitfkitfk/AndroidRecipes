package com.wizmacau.androidrecipes.module;

import android.content.Context;
import android.location.LocationManager;

import com.wizmacau.androidrecipes.core.App;
import com.wizmacau.androidrecipes.core.ForApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by llitfkitfk on 6/23/15.
 */
@Module
public class AndroidModule {
    private final App app;

    public AndroidModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManger() {
        return (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
    }
}
