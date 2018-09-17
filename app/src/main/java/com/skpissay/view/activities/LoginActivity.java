package com.skpissay.view.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.ActivityLoginBinding;
import com.skpissay.di.component.DaggerLoginComponent;
import com.skpissay.di.component.DaggerMainComponent;
import com.skpissay.di.module.LoginModule;
import com.skpissay.di.module.MainModule;
import com.skpissay.model.api.entity.Data;
import com.skpissay.model.api.entity.Places;
import com.skpissay.model.api.entity.Tag;
import com.skpissay.util.Constants;
import com.skpissay.util.DialogBuilder;
import com.skpissay.view.base.BaseActivity;
import com.skpissay.viewmodel.login.LoginViewModel;
import com.skpissay.viewmodel.main.MainViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class LoginActivity extends BaseActivity implements LoginViewModel.LoginListener {

    ActivityLoginBinding binding;

    MaterialDialog webviewProgress;

    @Inject
    LoginViewModel viewModel;

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initializeInjectors();
        initializeViews(savedInstanceState);
        binding.setViewModel(viewModel);
    }

    private void initializeInjectors() {
        DaggerLoginComponent.builder()
                .appComponent(((HeroApp) getApplication()).getAppComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    private void initializeViews(Bundle savedInstance) {
    }

    @Override
    public void onTagItemClicked(Tag tag) {

    }

    @Override
    public void onLogin(Places response) {
        if (response != null) {
            Data data = (new Gson()).fromJson(response.getTABLEDATA(), Data.class);
            Intent intent = new Intent(this, SplashActivity.class);
            intent.putExtra("DATA", Parcels.wrap(data));
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }
}
