package com.skpissay.di.component;

import dagger.Component;
import com.skpissay.di.ActivityScope;
import com.skpissay.di.module.SplashModule;
import com.skpissay.view.activities.SplashActivity;

/**
 * Created by mertsimsek on 13/01/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
