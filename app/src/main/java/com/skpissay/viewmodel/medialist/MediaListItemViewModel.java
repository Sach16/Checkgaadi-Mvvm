package com.skpissay.viewmodel.medialist;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.skpissay.R;
import com.skpissay.model.api.entity.ImageModel;
import com.skpissay.model.api.entity.Media;
import com.skpissay.model.api.entity.Result;
import com.skpissay.util.RxBus;
import com.skpissay.viewmodel.searchtag.TagClickEvent;

import java.util.List;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListItemViewModel {

    public ObservableField<String> userName;
    public ObservableField<String> address;

    private List<String> media;
    private RxBus rxBus;

    public MediaListItemViewModel(Context context, RxBus rxBus, List<String> media) {
        this.media = media;
        this.rxBus = rxBus;

        userName = new ObservableField<>(String.format("%s, %s", media.get(0), media.get(1)));
        address = new ObservableField<>(String.format("%s, %s, %s", media.get(2), media.get(3), media.get(4)));
    }

    public void onItemClicked(View view) {
        rxBus.send(new Object[]{view, media});
    }
}
