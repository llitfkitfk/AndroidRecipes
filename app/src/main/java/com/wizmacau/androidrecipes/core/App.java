package com.wizmacau.androidrecipes.core;

import android.app.Application;
import android.location.LocationManager;

import com.wizmacau.androidrecipes.MainActivity;
import com.wizmacau.androidrecipes.module.AndroidModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by llitfkitfk on 6/23/15.
 */
public class App extends Application {

    @Singleton
    @Component(modules = AndroidModule.class)
    public interface AppComponent {
        void inject(App application);
        void inject(MainActivity mainActivity);
    }

    @Inject
    LocationManager locationManager;

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApp_AppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
        component().inject(this);
    }

    public AppComponent component() {
        return component;
    }
}
