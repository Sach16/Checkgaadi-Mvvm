package com.skpissay.domain.medialist;

import com.skpissay.model.api.entity.MediaListResponse;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface MediaListUsecase {

    Observable<MediaListResponse> searchMedia(String tag);
}
