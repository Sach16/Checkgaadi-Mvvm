package com.skpissay.di.module;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.skpissay.di.ActivityScope;
import com.skpissay.di.FragmentScope;
import com.skpissay.domain.placesearch.PlaceSearchUsecase;
import com.skpissay.domain.placesearch.PlaceSearchUsecaseImpl;
import com.skpissay.domain.searchtag.SearchTagUsecase;
import com.skpissay.domain.searchtag.SearchTagUsecaseImpl;
import com.skpissay.model.api.ApiSource;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.model.preferences.PreferencesHelperImpl;
import com.skpissay.util.RxBus;
import com.skpissay.view.adapters.MainPagerAdapter;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.view.base.BaseFragment;
import com.skpissay.viewmodel.main.MainViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Module
public class MainModule {

    MainViewModel.MainListener mainListener;
    FragmentManager mFragment;

    public MainModule(MainViewModel.MainListener mainListener, FragmentManager pFragment) {
        this.mainListener = mainListener;
        this.mFragment = pFragment;
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
    MainViewModel provideMainViewModel(RxBus rxBus, PlaceSearchUsecase placeSearchUsecase){
        return new MainViewModel(rxBus, mainListener, placeSearchUsecase);
    }

    @Provides
    @ActivityScope
    MediaListAdapter provideMediaListAdapter(RxBus rxBus){
        return new MediaListAdapter(rxBus);
    }

    @Provides
    @ActivityScope
    MainPagerAdapter provideMainPagerAdapter(){
        return new MainPagerAdapter(mFragment);
    }
}
