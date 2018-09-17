package com.skpissay.di.module;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

import com.skpissay.di.ActivityScope;
import com.skpissay.di.FragmentScope;
import com.skpissay.domain.medialist.MediaListUsecase;
import com.skpissay.domain.medialist.MediaListUsecaseImpl;
import com.skpissay.model.api.ApiSource;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.model.preferences.PreferencesHelperImpl;
import com.skpissay.util.RxBus;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.viewmodel.medialist.MediaListViewModel;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Module
public class MediaListModule {

    MediaListViewModel.MediaListListener listener;
    MediaListViewModel.MediaListClickListener clicklistener;

    public MediaListModule(MediaListViewModel.MediaListListener listener, MediaListViewModel.MediaListClickListener clicklistener) {
        this.listener = listener;
        this.clicklistener = clicklistener;
    }

    @Provides
    @ActivityScope
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences preferences, Gson gson){
        return new PreferencesHelperImpl<>(preferences, gson);
    }

    @Provides
    @ActivityScope
    MediaListUsecase provideMediaListUsecase(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper){
        return new MediaListUsecaseImpl(apiSource, tokenPreferencesHelper);
    }

    @Provides
    @ActivityScope
    MediaListViewModel provideMediaListViewModel(MediaListUsecase mediaListUsecase, RxBus rxBus){
        return new MediaListViewModel(mediaListUsecase, rxBus, listener, clicklistener);
    }

    @Provides
    @ActivityScope
    MediaListAdapter provideMediaListAdapter(RxBus rxBus){
        return new MediaListAdapter(rxBus);
    }
}
