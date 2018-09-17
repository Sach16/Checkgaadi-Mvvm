package com.skpissay;

import android.app.Application;

import com.skpissay.di.component.AppComponent;
import com.skpissay.di.component.DaggerAppComponent;
import com.skpissay.di.module.AppModule;
import com.skpissay.di.module.NetworkModule;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class HeroApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
