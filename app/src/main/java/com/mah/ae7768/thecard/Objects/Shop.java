package com.mah.ae7768.thecard.Objects;

import android.content.Context;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class Shop {
    String id,name,address,url,phone,offer;
    int stampcount;
    LatLong pos;
    OpenHours openHours;
    GridFSDBFile logo,stampImg,cardImg;

    public void Shop(){
        openHours = new OpenHours();
    }

}
