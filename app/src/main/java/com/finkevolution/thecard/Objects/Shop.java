package com.finkevolution.thecard.Objects;

import com.finkevolution.thecard.Activites.MainActivity;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.Serializable;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class Shop implements Serializable{
    String id,name,address,url,phone, cardDescription;
    int stampcount;
    LatLong pos;
    OpenHours openHours;
    GridFSDBFile logo,stampImg,cardImg;

    int imgSource;

    public Shop(String id, String name, String test){
        openHours = new OpenHours();
        this.id = id;
        this.name = name;

        imgSource = MainActivity.getContext().getResources().getIdentifier(test, "drawable", MainActivity.getContext().getPackageName());

    }

    public void setOpenHours(String monday, String tuesday , String wednesday,
                             String thursday ,String friday, String saturday, String sunday){
        openHours.setMondayHours(monday);
        openHours.setTuesdayHours(tuesday);
        openHours.setWednesdayHours(wednesday);
        openHours.setThursdayHours(thursday);
        openHours.setFridayHours(friday);
        openHours.setSaturdayHours(saturday);
        openHours.setSundayHours(sunday);
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setURL(String url){
        this.url = url;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setCardDescription(String cardDescription){
        this.cardDescription = cardDescription;
    }

    public void setStampcount(int stampcount){
        this.stampcount = stampcount;
    }

    public void setPos(double longitude, double latitude){
        pos = new LatLong(longitude,latitude);
    }

    public void setLogo(GridFSDBFile logo){
        this.logo = logo;
    }

    public void setStampImg(GridFSDBFile stampImg){
        this.stampImg = stampImg;
    }

    public void setCardImg(GridFSDBFile cardImg){
        this.cardImg = cardImg;
    }

    public GridFSDBFile getCardImg() {
        return cardImg;
    }

    public GridFSDBFile getStampImg() {
        return stampImg;
    }

    public GridFSDBFile getLogo() {
        return logo;
    }

    public OpenHours getOpenHours() {
        return openHours;
    }

    public LatLong getPos() {
        return pos;
    }

    public int getStampcount() {
        return stampcount;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getImageSource(){
        return imgSource;
    }

}
