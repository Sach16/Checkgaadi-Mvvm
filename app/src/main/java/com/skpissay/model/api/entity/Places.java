package com.skpissay.model.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by S.K. Pissay on 09,August,2018.
 */
public class Places {

    @SerializedName("TABLE_DATA")
    @Expose
    private String tABLEDATA;

    public String getTABLEDATA() {
        return tABLEDATA;
    }

    public void setTABLEDATA(String tABLEDATA) {
        this.tABLEDATA = tABLEDATA;
    }
}