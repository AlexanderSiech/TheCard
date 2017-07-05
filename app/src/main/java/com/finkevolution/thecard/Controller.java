package com.finkevolution.thecard;

import com.finkevolution.thecard.Objects.Shop;
import com.finkevolution.thecard.Objects.User;

import com.finkevolution.thecard.TestClasses.ShopTest;
import com.finkevolution.thecard.TestClasses.TestRunner;
import com.finkevolution.thecard.TestClasses.UserTest;

import java.util.ArrayList;

/**
 * Created by alexander on 2017-06-28.
 */

public class Controller {
    private User user;
    private ArrayList<Shop> shops = new ArrayList<Shop>();

    public Controller(){
        user = new User("ID","johannes@gmail.com");
        createTestClasses();
        new TestRunner(user,shops);

    }

    public void createTestClasses(){
        UserTest ut = new UserTest(user.getIdentifier());
        ShopTest st = new ShopTest();
        shops = st.getFakeList();
        user = ut.setUserInformation();
    }

    public ArrayList<Shop> getShops(){
        return this.shops;
    }
}
