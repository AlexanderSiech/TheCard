package com.finkevolution.thecard.Objects;

import java.util.ArrayList;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

public class User {
    private String id,identifier;
    private ArrayList<Card>usersCards = new ArrayList<Card>();

    public User(String id, String identifier){
        this.id = id;
        this.identifier = identifier;
    }

    public void addCard(Card cardToAdd){
        usersCards.add(cardToAdd);
    }

    public void removeCard(String shopID){
        for(int i = 0 ; i<usersCards.size(); i++){
            if(usersCards.get(i).getShop().getId().equals(shopID)){
                usersCards.remove(i);
            }
        }
    }

    public Card getCardIndex(int i){
        return usersCards.get(i);
    }

    public String getId() {
        return this.id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getCardQuantity(){
        return usersCards.size();
    }
    public ArrayList<Card> getUserCards(){
        return this.usersCards;
    }
}
