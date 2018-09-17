package com.skpissay.viewmodel.searchtag;

import com.skpissay.model.api.entity.Tag;

/**
 * Created by mertsimsek on 14/01/17.
 */

public class TagClickEvent {
    public Tag tag;

    public TagClickEvent(Tag tag) {
        this.tag = tag;
    }
}
