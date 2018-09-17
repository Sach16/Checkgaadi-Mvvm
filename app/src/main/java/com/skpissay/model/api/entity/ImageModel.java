package com.skpissay.model.api.entity;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by S.K. Pissay on 23,July,2018.
 */

@Parcel
public class ImageModel {

    public String imageurl;
    public String thumburl;
    public String description;
    public Date timestamp;
    public String tag;
}
