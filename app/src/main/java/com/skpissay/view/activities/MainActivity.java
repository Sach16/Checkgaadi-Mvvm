package com.skpissay.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Message;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.ActivityMainBinding;
import com.skpissay.di.component.DaggerMainComponent;
import com.skpissay.di.module.MainModule;
import com.skpissay.model.api.entity.Data;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.Result;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.util.DialogBuilder;
import com.skpissay.view.adapters.MainPagerAdapter;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.view.base.BaseActivity;
import com.skpissay.view.fragments.MediaListFragment;
import com.skpissay.view.fragments.SearchTagFragment;
import com.skpissay.viewmodel.main.MainViewModel;

import org.parceler.Parcels;

import static android.content.ContentValues.TAG;

public class MainActivity extends BaseActivity implements MainViewModel.MainListener{

    ActivityMainBinding binding;

    @Inject
    MainViewModel viewModel;

    @Inject
    MediaListAdapter adapter;

    public static Intent newIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeInjectors();
        initializeViews(savedInstanceState);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    private void initializeInjectors() {
        DaggerMainComponent.builder()
                .appComponent(((HeroApp) getApplication()).getAppComponent())
                .mainModule(new MainModule(this, getSupportFragmentManager()))
                .build().inject(this);
    }

    private void initializeViews(Bundle savedInstance) {
        Data data = Parcels.unwrap(getIntent().getParcelableExtra("DATA"));

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        adapter.setMedias(data.getData());

//        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        fragment.getMapAsync(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    public void onTagItemClicked(Tag tag) {
    }

    @Override
    public void onPlace(Places response) {
    }

    @Override
    public void onError(Throwable error) {
        DialogBuilder
                .infoDialog(this,
                        R.string.dialog_error_title,
                        R.string.dialog_error_content)
                .show();
    }
}
