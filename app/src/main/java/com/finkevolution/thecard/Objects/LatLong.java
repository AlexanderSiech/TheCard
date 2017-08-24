package com.finkevolution.thecard.Objects;

import java.io.Serializable;

/**
 * Created by FinkEvolution on 2017-06-29.
 */

public class LatLong implements Serializable{
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

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.longitude = latitude;
    }
}
