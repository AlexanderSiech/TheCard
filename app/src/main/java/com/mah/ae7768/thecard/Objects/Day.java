package com.mah.ae7768.thecard.Objects;

import com.mah.ae7768.thecard.Activites.MainActivity;
import com.mah.ae7768.thecard.R;

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
        if(openHours != null){
            return openHours;
        }else{
            return MainActivity.getContext().getString(R.string.closed);
        }
    }
}
