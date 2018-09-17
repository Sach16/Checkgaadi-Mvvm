package com.skpissay.di.component;

import dagger.Component;
import com.skpissay.di.FragmentScope;
import com.skpissay.di.module.SearchTagModule;
import com.skpissay.view.fragments.SearchTagFragment;

/**
 * Created by mertsimsek on 13/01/17.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = SearchTagModule.class)
public interface SearchTagComponent {
    void inject(SearchTagFragment searchTagFragment);
}
