package com.skpissay.di.component;

import dagger.Component;
import com.skpissay.di.ActivityScope;
import com.skpissay.di.module.MainModule;
import com.skpissay.view.activities.MainActivity;

/**
 * Created by mertsimsek on 13/01/17.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
