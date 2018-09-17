package com.skpissay.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.parceler.Parcels;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.FragmentMediaListBinding;
import com.skpissay.di.component.DaggerMediaListComponent;
import com.skpissay.di.module.MediaListModule;
import com.skpissay.model.api.entity.Data;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.view.base.BaseActivity;
import com.skpissay.view.fragments.MediaListFragment;
import com.skpissay.viewmodel.medialist.MediaListViewModel;
import com.skpissay.viewmodel.splash.SplashViewModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by mertsimsek on 13/01/17.
 */
public class MediaListActivity extends BaseActivity implements OnMapReadyCallback, MediaListViewModel.MediaListClickListener, MediaListViewModel.MediaListListener {

    FragmentMediaListBinding binding;

    @Inject
    MediaListViewModel viewModel;

    List<String> strings;

    GoogleMap googleMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    LatLng lastFoundLocation;

    public static Intent newIntent(Context context, List<String> data) {
        Intent intent = new Intent(context, MediaListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("DATA", Parcels.wrap(data));
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_media_list);
        initializeInjectors();
        initializeViews();
        binding.setViewModel(viewModel);
    }

    private void initializeInjectors() {
        strings = Parcels.unwrap(getIntent().getParcelableExtra("DATA"));
        DaggerMediaListComponent.builder()
                .appComponent(((HeroApp) getApplication()).getAppComponent())
                .mediaListModule(new MediaListModule(this, this))
                .build().inject(this);
    }

    private void initializeViews() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        lastFoundLocation = getLocationFromAddress(this, strings.get(2));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastFoundLocation, 15f));
        viewModel.loadMedias(strings);
    }

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onMediaListLoaded(View view, ImageModel imageModel) {

    }

    @Override
    public void onCamera() {

    }
}
