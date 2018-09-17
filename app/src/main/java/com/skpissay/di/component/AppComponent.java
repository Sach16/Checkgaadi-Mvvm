package com.skpissay.di.component;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import com.skpissay.di.module.AppModule;
import com.skpissay.di.module.NetworkModule;
import com.skpissay.model.api.ApiSource;
import com.skpissay.util.RxBus;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ApiSource apiSource();
    SharedPreferences sharedPreferences();
    Gson gson();
    RxBus bus();
}
