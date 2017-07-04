package com.finkevolution.thecard.TestClasses;

import com.finkevolution.thecard.Objects.Shop;
import com.finkevolution.thecard.Objects.User;

import java.util.ArrayList;

/**
 * Created by Girondins on 04/07/17.
 */

public class TestRunner {

    public TestRunner(User user, ArrayList<Shop> shops){
        runUser(user);
        runShops(shops);
    }

    public void runUser(User user){
        System.out.println(" TESTING USER ----------");
        System.out.println("User ID: " + user.getId());
        System.out.println("User Identifier: " + user.getIdentifier());
        System.out.println("User Cardcount: " + user.getCardQuantity());


        System.out.println(" CHECKING CARDS ------------");
        for(int i = 0 ; i<user.getCardQuantity(); i++){
            System.out.println("Card for Shop: " + user.getCardIndex(i).getShop().getName());
            System.out.println("Stamps Collected: " + user.getCardIndex(i).getStampsCollected());
            System.out.println("Shop is Favorite: " + user.getCardIndex(i).isFavorite());
        }

    }

    public void runShops(ArrayList<Shop> shops){
        System.out.println(" CHECKING SHOPS -------------");

        for(int i = 0 ; i < shops.size(); i ++){
            System.out.println("Shop name: " + shops.get(i).getName());
            System.out.println("Shop id: " + shops.get(i).getId());
            System.out.println("Shop address: " + shops.get(i).getAddress());
            System.out.println("Shop phonenumber: " + shops.get(i).getPhone());
            System.out.println("Shop URL: " + shops.get(i).getUrl());
            System.out.println("Shop CardDescript: " + shops.get(i).getCardDescription());
            System.out.println("Shop Stamps Needed: " + shops.get(i).getStampcount());
            System.out.println("Shop Pos: " + "LAT: " +shops.get(i).getPos().getLatitude() + " LONG: " + shops.get(i).getPos().getLongitude());
            System.out.println("Shop Openhours -----------");

            for(int y=0; y< shops.get(i).getOpenHours().getSize(); y++){
                System.out.println(shops.get(i).getOpenHours().getDayAtIndex(y).getDayOfWeek() + " - " + shops.get(i).getOpenHours().getDayAtIndex(y).getOpenHours());
            }
        }



    }
}
