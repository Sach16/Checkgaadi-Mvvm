package com.skpissay.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import javax.inject.Inject;

import com.skpissay.HeroApp;
import com.skpissay.R;
import com.skpissay.databinding.FragmentSearchTagBinding;
import com.skpissay.di.component.DaggerSearchTagComponent;
import com.skpissay.di.module.SearchTagModule;
import com.skpissay.model.api.entity.TagSearchResponse;
import com.skpissay.util.DialogBuilder;
import com.skpissay.view.adapters.SearchTagAdapter;
import com.skpissay.viewmodel.searchtag.SearchTagViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagFragment extends Fragment implements SearchTagViewModel.SearchTagListener {

    public static final String KEY_STATE_LIST = "key_state_list";

    FragmentSearchTagBinding binding;

    @Inject
    SearchTagViewModel viewModel;

    @Inject
    SearchTagAdapter adapter;

    public static SearchTagFragment newInstance() {
        Bundle args = new Bundle();
        SearchTagFragment fragment = new SearchTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_tag, container, false);
        initializeInjectors();
        binding.setViewModel(viewModel);
        binding.recyclerView.setAdapter(adapter);

        if (savedInstanceState != null)
            adapter.setTags(Parcels.unwrap(savedInstanceState.getParcelable(KEY_STATE_LIST)));

        return binding.getRoot();
    }

    private void initializeInjectors() {
        DaggerSearchTagComponent.builder()
                .appComponent(((HeroApp) getActivity().getApplication()).getAppComponent())
                .searchTagModule(new SearchTagModule(this))
                .build().inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null && adapter.getTags() != null)
            outState.putParcelable(KEY_STATE_LIST, Parcels.wrap(adapter.getTags()));
    }

    @Override
    public void onTagListLoaded(TagSearchResponse response) {
        adapter.setTags(response.data);
    }

    @Override
    public void onError(Throwable error) {
        DialogBuilder
                .infoDialog(getActivity(),
                        R.string.dialog_error_title,
                        R.string.dialog_error_content)
                .show();
    }
}
