package com.finkevolution.thecard.Objects;

import com.finkevolution.thecard.Activites.MainActivity;
import com.finkevolution.thecard.R;

import java.io.Serializable;

/**
 * Created by FinkEvolution on 2017-06-29.
 */

public class Day implements Serializable{
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
