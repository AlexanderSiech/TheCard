package com.finkevolution.thecard;

import com.finkevolution.thecard.Activites.MainActivity;
import com.finkevolution.thecard.Objects.Card;
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

    public Controller(MainActivity mainActivity){
        user = new User("ID","johannes@gmail.com");
        this.mainActivity = mainActivity;
        createTestClasses();
        convertShopToCard();
        retrieveUserFavorites();
        new TestRunner(user,shops);

    }

    public void createTestClasses(){
        UserTest ut = new UserTest(user.getIdentifier());
        ShopTest st = new ShopTest();
        shops = st.getFakeList();
        user = ut.setUserInformation();
    }

    public void convertShopToCard(){
        for(int i = 0 ; i<shops.size(); i++){
            cards.add(new Card(shops.get(i),0,false));
        }
    }

    public void retrieveUserFavorites(){
        for(int i = 0; i<user.getCardQuantity(); i++){
            if(user.getCardIndex(i).isFavorite() == true){
                favorites.add(user.getCardIndex(i));
            }
        }
    }

    public ArrayList<Card> getShops(){
        return this.cards;
    }

    public ArrayList<Card> getUserCards(){
        return user.getUserCards();
    }

    public ArrayList<Card> getUserFavorites(){
        return this.favorites;
    }

    public void inflateStub(){
        mainActivity.inflateStub();
    }


}
