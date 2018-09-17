package com.skpissay.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import com.skpissay.di.ActivityScope;
import com.skpissay.domain.splash.SplashUsecase;
import com.skpissay.domain.splash.SplashUsecaseImpl;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.model.preferences.PreferencesHelperImpl;
import com.skpissay.util.RxBus;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.viewmodel.splash.SplashViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Module
public class SplashModule {

    SplashViewModel.SplashListener listener;

    public SplashModule(SplashViewModel.SplashListener listener) {
        this.listener = listener;
    }

    @Provides
    @ActivityScope
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson){
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @ActivityScope
    SplashUsecase provideSplashUsecase(PreferencesHelper<String> tokenPreferencesHelper){
        return new SplashUsecaseImpl(tokenPreferencesHelper);
    }

    @Provides
    @ActivityScope
    SplashViewModel provideSplashViewModel(SplashUsecase splashUsecase, RxBus rxBus){
        return new SplashViewModel(splashUsecase, listener, rxBus);
    }

    @Provides
    @ActivityScope
    MediaListAdapter provideMediaListAdapter(RxBus rxBus){
        return new MediaListAdapter(rxBus);
    }
}
