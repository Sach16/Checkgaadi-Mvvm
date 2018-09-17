package com.skpissay.domain.splash;

import javax.inject.Inject;

import com.skpissay.model.preferences.PreferencesHelper;
import com.skpissay.util.Constants;
import com.skpissay.util.RxTransformer;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashUsecaseImpl implements SplashUsecase{

    PreferencesHelper<String> tokenHelper;

    @Inject
    public SplashUsecaseImpl(PreferencesHelper<String> tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @Override
    public Observable<String> saveToken(String token) {
        return tokenHelper.save(Constants.SHARED_KEY_TOKEN, token)
                .compose(RxTransformer.applyComputationSchedulers());
    }
}
