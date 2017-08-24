package com.finkevolution.thecard.TestClasses;

import com.finkevolution.thecard.Objects.Shop;

import java.util.ArrayList;

/**
 * Created by Girondins on 04/07/17.
 */

public class ShopTest {
    private ArrayList<Shop> shopTestList = new ArrayList<Shop>();

    public ShopTest(){
        populateFakeShop();
    }

    public void populateFakeShop(){
        Shop test1 = new Shop("ID1","Poms Mackor","pomsklipp");
        test1.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test1.setAddress("Johannesvägen 23, Malmö");
        test1.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBLBLALBLBLALBLLBLSLBALSBLASBLLBL");
        test1.setCardDescription("Köp 10 Mackor, få den 11 gratis!");
        test1.setPhone("040-403203");
        test1.setStampcount(10);
        test1.setURL("www.pomsmackor.se");
        test1.setPos(44.02,23.00);


        Shop test2 = new Shop("ID2","Tea Junkie","teajunkieclean");
        test2.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test2.setAddress("Johannesvägen 23, Malmö");
        test2.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test2.setCardDescription("Köp Té, få 5 gratis!");
        test2.setPhone("040-403203");
        test2.setStampcount(5);
        test2.setURL("www.teajunkie.se");
        test2.setPos(23.30,10.00);


        Shop test3 = new Shop("ID3","Grönt och Gott","goggreen");
        test3.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test3.setAddress("Johannesvägen 23, Malmö");
        test3.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test3.setCardDescription("Köp 10 sallader, få den 11 gratis!");
        test3.setPhone("040-403203");
        test3.setStampcount(10);
        test3.setURL("www.grontogott.se");
        test3.setPos(44.02,23.00);


        Shop test4 = new Shop("ID4","Restaurang Niagara", "gogopink");
        test4.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test4.setAddress("Johannesvägen 23, Malmö");
        test4.setCardDescription("Köp Tillgodokort för 450:-, få 10 tillfällen");
        test4.setPhone("040-403203");
        test4.setStampcount(10);
        test4.setURL("www.restaurangniagara.se");
        test4.setPos(44.02,23.00);


        Shop test5 = new Shop("ID5","Papi", "papi");
        test5.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test5.setAddress("Johannesvägen 23, Malmö");
        test5.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test5.setCardDescription("Köp 7 menyrätter och få 50% på kvittot vid nästa tillfälle");
        test5.setPhone("040-403203");
        test5.setStampcount(7);
        test5.setURL("www.papi.se");
        test5.setPos(44.02,23.00);


        Shop test6 = new Shop("ID6","Favvo Glass", "favvoclean");
        test6.setOpenHours("10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 17:00","10:00 - 20:00","11:00 - 21:00",null);
        test6.setAddress("Johannesvägen 23, Malmö");
        test6.setShopDescription("BLALBLBLALBLLBLSLBALSBLASBLLBL");
        test6.setCardDescription("Köp 5 mjukglasser och få en 3 kulor bägare gratis");
        test6.setPhone("040-403203");
        test6.setStampcount(5);
        test6.setURL("www.favvoglass.se");
        test6.setPos(44.02,23.00);


        shopTestList.add(test1);
        shopTestList.add(test2);
        shopTestList.add(test3);
        shopTestList.add(test4);
        shopTestList.add(test5);
        shopTestList.add(test6);
    }

    public ArrayList<Shop> getFakeList(){
        return this.shopTestList;
    }
}
