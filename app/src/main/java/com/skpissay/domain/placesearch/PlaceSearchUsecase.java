package com.skpissay.domain.placesearch;

import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.model.api.entity.User;

import rx.Observable;

/**
 * Created by S.K. Pissay on 09,August,2018.
 */
public interface PlaceSearchUsecase {

    Observable<Places> searchPlace(User body);
}
