package com.skpissay.model.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by S.K. Pissay on 17,September,2018.
 */
@Parcel
public class Data {

    @SerializedName("data")
    @Expose
    public List<List<String>> data = null;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

}
