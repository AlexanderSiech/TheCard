package com.finkevolution.thecard.TestClasses;

import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.Objects.Shop;
import com.finkevolution.thecard.Objects.User;

/**
 * Created by Girondins on 04/07/17.
 */

public class UserTest {
    User user;
    Shop test1,test2,test3,test4;

    public UserTest(String userName){
        this.user = new User("ID",userName);
        createFakeShops();
        createFakeCards();
    }

    public void createFakeCards(){
        user.addCard(new Card(test1,4,true));
        user.addCard(new Card(test2,0,true));
        user.addCard(new Card(test3,9,true));
        user.addCard(new Card(test4,2,false));
    }

    public void createFakeShops(){

        test1 = new Shop("ID1","Poms Mackor", "pomsklipp");
        test1.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test1.setAddress("Johannesvägen 23, Malmö");
        test1.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBL");
        test1.setCardDescription("Köp 10 Mackor, få den 11 gratis!");
        test1.setPhone("040-403203");
        test1.setStampcount(10);
        test1.setURL("www.pomsmackor.se");
        test1.setPos(44.02,23.00);


        test2 = new Shop("ID2","Tea Junkie", "teajunkieclean");
        test2.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test2.setAddress("Johannesvägen 23, Malmö");
        test2.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test2.setCardDescription("Köp Té, få 5 gratis!");
        test2.setPhone("040-403203");
        test2.setStampcount(5);
        test2.setURL("www.teajunkie.se");
        test2.setPos(23.30,10.00);


        test3 = new Shop("ID3","Grönt och Gott", "goggreen");
        test3.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test3.setAddress("Johannesvägen 23, Malmö");
        test3.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test3.setCardDescription("Köp 10 sallader, få den 11 gratis!");
        test3.setPhone("040-403203");
        test3.setStampcount(10);
        test3.setURL("www.grontogott.se");
        test3.setPos(44.02,23.00);


        test4 = new Shop("ID4","Restaurang Niagara", "gogopink");
        test4.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test4.setAddress("Johannesvägen 23, Malmö");
        test4.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test4.setCardDescription("Köp Tillgodokort för 450:-, få 10 tillfällen");
        test4.setPhone("040-403203");
        test4.setStampcount(10);
        test4.setURL("www.restaurangniagara.se");
        test4.setPos(44.02,23.00);
    }

    public User setUserInformation(){
        return this.user;
    }

}
