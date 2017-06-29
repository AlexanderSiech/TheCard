package com.finkevolution.thecard.Objects;

import com.finkevolution.thecard.Activites.MainActivity;
import com.finkevolution.thecard.R;

import java.util.ArrayList;

/**
 * Created by FinkEvolution on 2017-06-29.
 */

public class OpenHours {
    ArrayList<Day> openHours = new ArrayList<Day>();

    public OpenHours(){
        Day Monday = new Day(MainActivity.getContext().getString(R.string.monday));
        Day Tuesday = new Day(MainActivity.getContext().getString(R.string.tuesday));
        Day Wednesday = new Day(MainActivity.getContext().getString(R.string.wednesday));
        Day Thursday = new Day(MainActivity.getContext().getString(R.string.thursday));
        Day Friday = new Day(MainActivity.getContext().getString(R.string.friday));
        Day Saturday = new Day(MainActivity.getContext().getString(R.string.saturday));
        Day Sunday = new Day(MainActivity.getContext().getString(R.string.sunday));
        openHours.add(Monday);
        openHours.add(Tuesday);
        openHours.add(Wednesday);
        openHours.add(Thursday);
        openHours.add(Friday);
        openHours.add(Saturday);
        openHours.add(Sunday);
    }

    public void setMondayHours(String mondayHours){
        openHours.get(0).setOpenHours(mondayHours);
    }

    public void setTuesdayHours(String tuesdayHours){
        openHours.get(1).setOpenHours(tuesdayHours);
    }

    public void setWednesdayHours(String wednesdayHours){
        openHours.get(2).setOpenHours(wednesdayHours);
    }

    public void setThursdayHours(String thursdayHours){
        openHours.get(3).setOpenHours(thursdayHours);
    }

    public void setFridayHours(String fridayHours){
        openHours.get(4).setOpenHours(fridayHours);
    }

    public void setSaturdayHours(String saturdayHours){
        openHours.get(5).setOpenHours(saturdayHours);
    }

    public void setSundayHours(String sundayHours){
        openHours.get(6).setOpenHours(sundayHours);
    }


    public Day getDayAtIndex(int i){
        return openHours.get(i);
    }

    public int getSize(){
        return openHours.size();
    }
}
