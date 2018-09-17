package com.skpissay.di.module;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;

import com.google.gson.Gson;
import com.skpissay.di.ActivityScope;
import com.skpissay.domain.placesearch.PlaceSearchUsecase;
import com.skpissay.domain.placesearch.PlaceSearchUsecaseImpl;
import com.skpissay.model.api.ApiSource;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.model.preferences.PreferencesHelperImpl;
import com.skpissay.util.RxBus;
import com.skpissay.view.adapters.MainPagerAdapter;
import com.skpissay.viewmodel.login.LoginViewModel;
import com.skpissay.viewmodel.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by S.K. Pissay on 16,September,2018.
 */
@Module
public class LoginModule {

    LoginViewModel.LoginListener loginListener;

    public LoginModule(LoginViewModel.LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Provides
    @ActivityScope
    PreferencesHelper<Places> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson){
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @ActivityScope
    PlaceSearchUsecase providePlaceSearchUsecase(PreferencesHelper<Places> tokenHelper, ApiSource apiSource){
        return new PlaceSearchUsecaseImpl(tokenHelper, apiSource);
    }

    @Provides
    @ActivityScope
    LoginViewModel provideMainViewModel(RxBus rxBus, PlaceSearchUsecase placeSearchUsecase){
        return new LoginViewModel(rxBus, loginListener, placeSearchUsecase);
    }
}

