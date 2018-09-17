package com.skpissay.viewmodel.splash;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import com.skpissay.domain.splash.SplashUsecase;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.util.RxBus;

import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashViewModel {

    private CompositeSubscription subscription;
    private SplashUsecase usecase;
    private SplashListener listener;
    private RxBus rxBus;
    private ImageModel imageModel;
    public String image, name, tag;

    @Inject
    public SplashViewModel(SplashUsecase usecase, SplashListener listener, RxBus rxBus) {
        this.usecase = usecase;
        this.listener = listener;
        this.rxBus = rxBus;
        subscription = new CompositeSubscription();
        this.rxBus.toObserverable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        listener.onItemClick((View) getOA(o)[0], (List<String>) getOA(o)[1]);
                    }
                });
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public interface SplashListener {

        void onItemClick(View view, List<String> imageModel);

    }

    public Object[] getOA(Object o){
        return (Object[]) o;
    }

}
