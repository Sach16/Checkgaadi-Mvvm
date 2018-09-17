package com.skpissay.domain.placesearch;

import com.skpissay.domain.searchtag.SearchTagUsecase;
import com.skpissay.model.api.ApiSource;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.model.api.entity.User;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.util.Constants;
import com.skpissay.util.RxTransformer;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by S.K. Pissay on 09,August,2018.
 */
public class PlaceSearchUsecaseImpl implements PlaceSearchUsecase {

    private PreferencesHelper<Places> tokenHelper;
    private ApiSource apiSource;

    @Inject
    public PlaceSearchUsecaseImpl(PreferencesHelper<Places> tokenHelper, ApiSource apiSource) {
        this.tokenHelper = tokenHelper;
        this.apiSource = apiSource;
    }

    @Override
    public Observable<Places> searchPlace(User body) {
        return tokenHelper.get(Constants.SHARED_KEY_TOKEN, Places.class)
                .flatMap(s -> apiSource.searchPlace(body))
                .compose(RxTransformer.applyIOSchedulers());
    }
}
