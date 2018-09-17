package com.skpissay.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.skpissay.databinding.ItemMediaListBinding;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.model.api.entity.Places;
import com.skpissay.util.RxBus;
import com.skpissay.viewmodel.medialist.MediaListItemViewModel;

/**
 * Created by mertsimsek on 14/01/17.
 */

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.MediaListViewHolder>{

    private List<List<String>> medias;
    private RxBus rxBus;

    public MediaListAdapter(RxBus rxBus) {
        this.rxBus = rxBus;
        medias = new ArrayList<>();
    }

    public void setMedias(List<List<String>> medias){
        this.medias = medias;
        notifyDataSetChanged();
    }

    public List<List<String>> getMedias(){
        return medias;
    }

    @Override
    public MediaListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MediaListViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(MediaListViewHolder holder, int position) {
        holder.bind(new MediaListItemViewModel(holder.itemView.getContext(), rxBus, medias.get(position)));
    }

    @Override
    public int getItemCount() {
        return medias.size();
    }

    static class MediaListViewHolder extends RecyclerView.ViewHolder{

        static MediaListAdapter.MediaListViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemMediaListBinding binding = ItemMediaListBinding.inflate(inflater, parent, false);
            return new MediaListAdapter.MediaListViewHolder(binding);
        }

        private ItemMediaListBinding binding;

        public MediaListViewHolder(ItemMediaListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MediaListItemViewModel viewModel){
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
