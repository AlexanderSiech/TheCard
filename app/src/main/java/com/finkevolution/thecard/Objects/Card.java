package com.finkevolution.thecard.Objects;

import java.io.Serializable;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class Card implements Serializable {
    private Shop shop;
    private int stampsCollected;
    private boolean isFavorite;

    public Card(Shop shop, int stampsCollected , boolean isFavorite){
        this.shop = shop;
        this.stampsCollected = stampsCollected;
        this.isFavorite = isFavorite;
    }

    public void setStampsCollected(int stampsCollected) {
        this.stampsCollected = stampsCollected;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Shop getShop(){
        return this.shop;
    }

    public int getStampsCollected() {
        return stampsCollected;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
