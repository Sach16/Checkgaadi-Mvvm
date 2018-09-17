package com.skpissay.di.module;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import com.skpissay.di.FragmentScope;
import com.skpissay.domain.searchtag.SearchTagUsecase;
import com.skpissay.domain.searchtag.SearchTagUsecaseImpl;
import com.skpissay.model.api.ApiSource;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.model.preferences.PreferencesHelperImpl;
import com.skpissay.util.RxBus;
import com.skpissay.view.adapters.SearchTagAdapter;
import com.skpissay.viewmodel.searchtag.SearchTagViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Module
public class SearchTagModule {

    SearchTagViewModel.SearchTagListener listener;

    public SearchTagModule(SearchTagViewModel.SearchTagListener listener) {
        this.listener = listener;
    }

    @Provides
    @FragmentScope
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson){
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @FragmentScope
    SearchTagUsecase provideSearchTagUsecase(PreferencesHelper<String> tokenHelper, ApiSource apiSource){
        return new SearchTagUsecaseImpl(tokenHelper, apiSource);
    }

    @Provides
    @FragmentScope
    SearchTagViewModel provideSearchTagViewModel(SearchTagUsecase usecase){
        return new SearchTagViewModel(usecase, listener);
    }

    @Provides
    @FragmentScope
    SearchTagAdapter provideSearchTagAdapter(RxBus rxBus){
        return new SearchTagAdapter(rxBus);
    }
}
