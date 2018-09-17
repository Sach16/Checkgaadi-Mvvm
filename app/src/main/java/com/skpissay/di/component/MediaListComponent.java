package com.skpissay.di.component;

import dagger.Component;

import com.skpissay.di.ActivityScope;
import com.skpissay.di.FragmentScope;
import com.skpissay.di.module.MediaListModule;
import com.skpissay.view.activities.MediaListActivity;
import com.skpissay.view.fragments.MediaListFragment;

/**
 * Created by mertsimsek on 14/01/17.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MediaListModule.class)
public interface MediaListComponent {
    void inject(MediaListActivity activity);
}
