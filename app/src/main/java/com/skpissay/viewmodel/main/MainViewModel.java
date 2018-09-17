package com.skpissay.viewmodel.main;

import android.databinding.ObservableField;
import android.location.Location;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.skpissay.domain.placesearch.PlaceSearchUsecase;
import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.util.RxBus;
import com.skpissay.view.fragments.MediaListFragment;
import com.skpissay.viewmodel.searchtag.TagClickEvent;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MainViewModel {

    private RxBus rxBus;
    private MainListener listener;
    private boolean isTwoPane = false;
    private CompositeSubscription subscription;
    private PlaceSearchUsecase placeSearchUsecase;
    private Location lastSavedLocation;
    public ObservableField<String> editText;

    public MainViewModel(RxBus rxBus, MainListener listener, PlaceSearchUsecase placeSearchUsecase) {
        this.rxBus = rxBus;
        this.listener = listener;
        this.placeSearchUsecase = placeSearchUsecase;
        subscription = new CompositeSubscription();
        editText = new ObservableField<>();

        subscription.add(rxBus.toObserverable()
                .filter(o -> o instanceof TagClickEvent)
                .map(o -> (TagClickEvent) o)
                .map(tagClickEvent -> tagClickEvent.tag)
                .subscribe(listener::onTagItemClicked));
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void lastSavedLocation(Location location){
        this.lastSavedLocation = location;
    }

    public void setTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }

    public interface MainListener {
        void onTagItemClicked(Tag tag);

        void onPlace(Places response);

        void onError(Throwable error);
    }
}
