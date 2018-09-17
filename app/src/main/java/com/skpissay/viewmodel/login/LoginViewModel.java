package com.skpissay.viewmodel.login;

import android.databinding.ObservableField;
import android.location.Location;
import android.view.View;

import com.google.gson.JsonObject;
import com.skpissay.domain.placesearch.PlaceSearchUsecase;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.model.api.entity.User;
import com.skpissay.util.RxBus;
import com.skpissay.viewmodel.main.MainViewModel;
import com.skpissay.viewmodel.searchtag.TagClickEvent;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by S.K. Pissay on 16,September,2018.
 */
public class LoginViewModel {

    private RxBus rxBus;
    private LoginViewModel.LoginListener listener;
    private boolean isTwoPane = false;
    private CompositeSubscription subscription;
    private PlaceSearchUsecase placeSearchUsecase;
    private Location lastSavedLocation;
    public ObservableField<String> username, password;

    public LoginViewModel(RxBus rxBus, LoginViewModel.LoginListener listener, PlaceSearchUsecase placeSearchUsecase) {
        this.rxBus = rxBus;
        this.listener = listener;
        this.placeSearchUsecase = placeSearchUsecase;
        subscription = new CompositeSubscription();
        username = new ObservableField<>();
        password = new ObservableField<>();

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

    public void lastSavedLocation(Location location) {
        this.lastSavedLocation = location;
    }

    public void setTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }

    public interface LoginListener {
        void onTagItemClicked(Tag tag);

        void onLogin(Places response);

        void onError(Throwable error);
    }

    public void onLogin(View view) {
        User user = new User();
        user.setUsername(username.get());
        user.setPassword(password.get());
        subscription.add(placeSearchUsecase.searchPlace(user)
                .subscribe(listener::onLogin, listener::onError));
    }
}
