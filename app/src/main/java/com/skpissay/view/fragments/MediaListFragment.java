package com.skpissay.view.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import javax.inject.Inject;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.FragmentMediaListBinding;
import com.skpissay.di.component.DaggerMediaListComponent;
import com.skpissay.di.module.MediaListModule;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.model.api.entity.MediaListResponse;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.util.DialogBuilder;
import com.skpissay.view.activities.SplashActivity;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.view.base.BaseFragment;
import com.skpissay.viewmodel.medialist.MediaListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListFragment extends BaseFragment implements MediaListViewModel.MediaListListener, MediaListViewModel.MediaListClickListener, OnMapReadyCallback{

    public static final String KEY_TAG = "key_tag";
    public static final String KEY_POS = "key_pos";
    public static final String KEY_OBJECT = "key_object";
    public static final String KEY_STATE_LIST = "key_state_list";

    FragmentMediaListBinding binding;

    @Inject
    MediaListViewModel viewModel;

    @Inject
    MediaListAdapter adapter;

    String mStrTab;

    private List<ImageModel> imageList = new ArrayList<>();

    GoogleMap googleMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location lastFoundLocation;

    public static MediaListFragment newInstance(int pPosition, Tag tag, Object pObject) {
        Bundle args = new Bundle();
        args.putInt(KEY_POS, pPosition);
        args.putParcelable(KEY_TAG, Parcels.wrap(tag));
        args.putParcelable(KEY_OBJECT, Parcels.wrap(pObject));
        MediaListFragment fragment = new MediaListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_media_list, container, false);
        initializeInjectors();
        initializeViews();
        binding.setViewModel(viewModel);

        /**
         * NOTE : Very nice explaination for orientation*/
        /*if (savedInstanceState == null)
            viewModel.loadMedias();
        else
            adapter.setMedias(Parcels.unwrap(savedInstanceState.getParcelable(KEY_STATE_LIST)));*/

        return binding.getRoot();
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    private void initializeViews() {
        LinearLayoutManager linearLayoutManager;
            linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        binding.recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initializeInjectors() {
        Tag tag = Parcels.unwrap(getArguments().getParcelable(KEY_TAG));
        mStrTab = Parcels.unwrap(getArguments().getParcelable(KEY_OBJECT));
/*
        DaggerMediaListComponent.builder()
                .appComponent(((HeroApp) getActivity().getApplication()).getAppComponent())
                .mediaListModule(new MediaListModule(this, this))
                .build().inject(this);
*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter.getMedias() != null)
            outState.putParcelable(KEY_STATE_LIST, Parcels.wrap(adapter.getMedias()));
    }

    @Override
    public void onMediaListLoaded(View view, ImageModel imageModel) {
        if (imageModel.tag.equals(mStrTab)) {
            Intent intent = new Intent(m_cObjMainActivity, SplashActivity.class);
            intent.putExtra("Media", Parcels.wrap(imageModel));
            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        getDeviceLocation();
    }

    public void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            Task locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener((Executor) this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        lastFoundLocation = (Location) task.getResult();
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(lastFoundLocation.getLatitude(),
                                        lastFoundLocation.getLongitude()), 15f));
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");
                        Log.e(TAG, "Exception: %s", task.getException());
                    }
                }
            });
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onCamera() {

    }
}
