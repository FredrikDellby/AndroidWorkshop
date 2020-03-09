
package com.example.workshopapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StopData {

    @SerializedName("LocationList")
    @Expose
    private LocationList locationList;

    public LocationList getLocationList() {
        return locationList;
    }

    public void setLocationList(LocationList locationList) {
        this.locationList = locationList;
    }

}
