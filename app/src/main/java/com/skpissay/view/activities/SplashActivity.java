package com.skpissay.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.ActivitySplashBinding;
import com.skpissay.di.component.DaggerSplashComponent;
import com.skpissay.di.module.SplashModule;
import com.skpissay.model.api.entity.Data;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.util.Constants;
import com.skpissay.view.adapters.MediaListAdapter;
import com.skpissay.viewmodel.splash.SplashViewModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashActivity extends AppCompatActivity implements SplashViewModel.SplashListener{

    private static int RC_LOGIN = 1001;

    ActivitySplashBinding binding;

    @Inject
    SplashViewModel viewModel;

    @Inject
    MediaListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initializeInjectors();
        initializeViews();
        binding.setViewModel(viewModel);
    }

    private void initializeInjectors(){
        HeroApp app = (HeroApp) getApplication();
        DaggerSplashComponent.builder()
                .appComponent(app.getAppComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);
    }

    private void initializeViews() {
        Data data = Parcels.unwrap(getIntent().getParcelableExtra("DATA"));

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        adapter.setMedias(data.getData());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    public void onItemClick(View view, List<String> imageModel) {
        startActivity(MediaListActivity.newIntent(this, imageModel));
    }
}
