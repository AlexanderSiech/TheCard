package com.finkevolution.thecard;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.finkevolution.thecard.Activites.CardlistFragment;
import com.finkevolution.thecard.Activites.ExpandedFragment;
import com.finkevolution.thecard.Activites.MainActivity;
import com.finkevolution.thecard.ExternalServices.NFC;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.Objects.LatLong;
import com.finkevolution.thecard.Objects.Shop;
import com.finkevolution.thecard.Objects.User;

import com.finkevolution.thecard.TestClasses.ShopTest;
import com.finkevolution.thecard.TestClasses.TestRunner;
import com.finkevolution.thecard.TestClasses.UserTest;

import java.util.ArrayList;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class Controller {
    private User user;
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Card> favorites = new ArrayList<Card>();
    private MainActivity mainActivity;
    private NFC nfcManager;
    private LatLong userLatLong;

    public Controller(MainActivity mainActivity) {
        user = new User("ID", "johannes@gmail.com");
        this.mainActivity = mainActivity;
        nfcManager = new NFC(mainActivity);
        nfcManager.start();
        createTestClasses();
        convertShopToCard();
        checkUserCard();
        retrieveUserFavorites();
        setupListFragment();
        new TestRunner(user, shops);

    }

    public void setupListFragment() {
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("cardlist", cards);
        CardlistFragment cardlistFragment = new CardlistFragment();
        cardlistFragment.setArguments(cardBundle);
        cardlistFragment.setController(this);
        mainActivity.setFragment(cardlistFragment, false);
    }

    public void createTestClasses() {
        UserTest ut = new UserTest(user.getIdentifier());
        ShopTest st = new ShopTest();
        shops = st.getFakeList();
        user = ut.setUserInformation();
    }

    public void convertShopToCard() {
        for (int i = 0; i < shops.size(); i++) {
            cards.add(new Card(shops.get(i), 0, false));
        }
    }

    public void retrieveUserFavorites() {
        for (int i = 0; i < user.getCardQuantity(); i++) {
            if (user.getCardIndex(i).isFavorite() == true) {
                favorites.add(user.getCardIndex(i));
            }
        }
    }

    public ArrayList<Card> getShops() {
        return this.cards;
    }

    public ArrayList<Card> getUserCards() {
        return user.getUserCards();
    }

    public ArrayList<Card> getUserFavorites() {
        return this.favorites;
    }

    public void checkUserCard() {
        for (int i = 0; i < user.getCardQuantity(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(j).getShop().getId().equals(user.getCardIndex(i).getShop().getId())) {
                    cards.set(j, user.getCardIndex(i));
                    System.out.println("SWAP" + " " + user.getCardIndex(i).getShop().getName() + " stamps: " + user.getCardIndex(i).getStampsCollected());
                }
            }
        }
    }

    public void expandCard(ImageView imageView, Card card) {
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card", card);
        ExpandedFragment expandedFragment = new ExpandedFragment();
        expandedFragment.setArguments(cardBundle);
        expandedFragment.setController(this);
        mainActivity.expandFragment(imageView, expandedFragment);
    }

    public void resolveIntent(Intent intent) {
        nfcManager.resolveIntent(intent);
    }

    public void requestNewPosition(){
        mainActivity.requestLocation();
    }

    public boolean checkCameraPermission(){
        return mainActivity.checkCameraPermission();
    }

    public void requestCameraPermission(){
        mainActivity.requestCameraPermission();
    }


    public void setUserLatLong(LatLong userLatLong){
        this.userLatLong = userLatLong;
        Log.d("UserLatLong", userLatLong.getLatitude() + " and " + userLatLong.getLongitude());
    }

    public LatLong getUserPosition(){
        Toast.makeText(mainActivity,"Returning Position " + userLatLong.getLatitude() + " and " + userLatLong.getLongitude(), Toast.LENGTH_SHORT).show();
        return userLatLong;
    }

}
