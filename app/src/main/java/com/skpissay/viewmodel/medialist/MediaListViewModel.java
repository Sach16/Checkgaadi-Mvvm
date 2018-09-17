package com.skpissay.viewmodel.medialist;

import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import com.skpissay.domain.medialist.MediaListUsecase;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.model.api.entity.Media;
import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.util.RxBus;

import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListViewModel {

//    public ObservableField<String> tagName;
    public ObservableField<Boolean> isLoading;
    public ObservableField<Boolean> refreshEnabled;

    private CompositeSubscription subscription;
    private MediaListUsecase mediaListUsecase;
    private MediaListListener listener;
    private RxBus rxBus;
    public ObservableField<String> userName;
    public ObservableField<String> address;

    @Inject
    public MediaListViewModel(MediaListUsecase mediaListUsecase, RxBus rxBus, MediaListListener listener, MediaListClickListener mediaListClickListener) {
        this.mediaListUsecase = mediaListUsecase;
        this.listener = listener;
        this.rxBus = rxBus;
        subscription = new CompositeSubscription();

        isLoading = new ObservableField<>(true);
        refreshEnabled = new ObservableField<>(true);
//        tagName = new ObservableField<>("#" + tag.name);
    }

    public void loadMedias(List<String> media){
        userName = new ObservableField<>(String.format("%s, %s", media.get(0), media.get(1)));
        address = new ObservableField<>(String.format("%s, %s, %s", media.get(2), media.get(3), media.get(4)));    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public interface MediaListListener {
        void onCamera();
    }

    public interface MediaListClickListener {
        void onMediaListLoaded(View view, ImageModel imageModel);
    }

    public Object[] getOA(Object o){
        return (Object[]) o;
    }

    public void onCamera(View view) {
        listener.onCamera();
    }
}
