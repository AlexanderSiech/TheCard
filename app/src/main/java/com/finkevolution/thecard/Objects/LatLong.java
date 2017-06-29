package com.finkevolution.thecard.Objects;

/**
 * Created by FinkEvolution on 2017-06-29.
 */

public class LatLong {
    public double longitude,latitude;

    public LatLong(double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }
}
