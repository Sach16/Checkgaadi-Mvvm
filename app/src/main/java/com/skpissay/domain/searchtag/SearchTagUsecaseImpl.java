package com.skpissay.domain.searchtag;

import javax.inject.Inject;

import com.skpissay.model.api.ApiSource;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.util.Constants;
import com.skpissay.util.RxTransformer;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagUsecaseImpl implements SearchTagUsecase {

    private PreferencesHelper<String> tokenHelper;
    private ApiSource apiSource;

    @Inject
    public SearchTagUsecaseImpl(PreferencesHelper<String> tokenHelper, ApiSource apiSource) {
        this.tokenHelper = tokenHelper;
        this.apiSource = apiSource;
    }

    @Override
    public Observable<TagSearchResponse> searchTag(String query) {
        return tokenHelper.get(Constants.SHARED_KEY_TOKEN, String.class)
                .flatMap(s -> apiSource.searchTag(query, s))
                .compose(RxTransformer.applyIOSchedulers());
    }
}
