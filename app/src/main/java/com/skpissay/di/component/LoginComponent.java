package com.skpissay.di.component;

import com.skpissay.di.ActivityScope;
import com.skpissay.di.module.LoginModule;
import com.skpissay.di.module.MainModule;
import com.skpissay.view.activities.LoginActivity;
import com.skpissay.view.activities.MainActivity;

import dagger.Component;

/**
 * Created by S.K. Pissay on 17,September,2018.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
