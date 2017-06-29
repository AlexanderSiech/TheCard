package com.mah.ae7768.thecard.Objects;

/**
 * Created by Girondins on 2017-06-29.
 */

public class Day {
    String dayOfWeek,openHours;

    public Day(String day){
        this.dayOfWeek = day;
    }

    public String getDayOfWeek(){
        return this.dayOfWeek;
    }

    public void setOpenHours(String openHours){
        this.openHours = openHours;
    }

    public String getOpenHours(){
        return this.openHours;
    }
}
