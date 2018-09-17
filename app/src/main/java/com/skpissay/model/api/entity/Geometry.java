package com.skpissay.model.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 09,August,2018.
 */
public class Geometry {

    @SerializedName("location")
    @Expose
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
