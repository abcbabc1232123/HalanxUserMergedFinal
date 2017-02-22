package com.halanx.tript.userapp;

/**
 * Created by samarthgupta on 23/02/17.
 */

public class LocationSend {
    double latitude;
    double longitude;

    public LocationSend(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }

}
