package com.skpissay.model.api;

import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.model.api.entity.User;

import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface ApiSource {

    Observable<TagSearchResponse> searchTag(String query, String token);

    Observable<MediaListResponse> searchMedia(String tag, String token);

    Observable<Places> searchPlace(User body);
}
