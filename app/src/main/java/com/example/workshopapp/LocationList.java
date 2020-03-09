
package com.example.workshopapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationList {

    @SerializedName("noNamespaceSchemaLocation")
    @Expose
    private String noNamespaceSchemaLocation;
    @SerializedName("server time")
    @Expose
    private String serverTime;
    @SerializedName("serverdate")
    @Expose
    private String serverdate;
    @SerializedName("StopLocation")
    @Expose
    private List<StopLocation> stopLocation = null;

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerdate() {
        return serverdate;
    }

    public void setServerdate(String serverdate) {
        this.serverdate = serverdate;
    }

    public List<StopLocation> getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(List<StopLocation> stopLocation) {
        this.stopLocation = stopLocation;
    }

}
