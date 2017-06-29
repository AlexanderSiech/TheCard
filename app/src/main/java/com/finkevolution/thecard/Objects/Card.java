package com.finkevolution.thecard.Objects;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class Card {
    private Shop shop;
    private int stampsCollected;
    private boolean isFavorite;

    public Card(Shop shop,int stampsCollected,boolean isFavorite){
        this.shop = shop;
        this.stampsCollected = stampsCollected;
        this.isFavorite = isFavorite;
    }

    public Shop getShop(){
        return this.shop;
    }
}
