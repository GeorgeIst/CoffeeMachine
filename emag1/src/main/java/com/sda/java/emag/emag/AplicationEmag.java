package com.sda.java.emag.emag;

import com.sda.java.emag.emag.businesslogic.Cart;
import com.sda.java.emag.emag.businesslogic.CartController;
import com.sda.java.emag.emag.businesslogic.User;
import com.sda.java.emag.emag.item.Color;
import com.sda.java.emag.emag.item.Phone;
import com.sda.java.emag.emag.item.Shoes;
import com.sda.java.emag.emag.businesslogic.Stock;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class AplicationEmag {
    public static final String PHONE_NAME = "S10";
    public static final float PRICE = 1000f;
    public static final float DISPLAY_SIZE = 5.5f;
    public static final String BRAND = "Samsung";
    public static final String CPU = "Snapdragon855";
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;
    public static final String SHOES_MODEL = "Nike air";
    public static final String SHOES_BRAND = "Nike";
    public static final int SHOES_PRICE = 200;
    public static final int SHOES_SIZE = 24;
    public static final Color SHOER_COLOR = Color.BLUE;
    public static final int AVAILABLE_QUANTITY = 100000;
    public static final int REQUESTED_QUANTITY = AVAILABLE_QUANTITY;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Object stockMutex = new Object();
        final Stock baneasaMall = new Stock(new ConcurrentHashMap<>(), stockMutex);

        System.out.println("Loaded items: " + baneasaMall.showItems());
        final Phone samsungS10 = new Phone(PHONE_NAME, PRICE, DISPLAY_SIZE, BRAND, CPU);
        //final Shoes nike = new Shoes(SHOES_MODEL, SHOES_PRICE, SHOES_BRAND, SHOES_SIZE, SHOER_COLOR);
        baneasaMall.addItem(samsungS10, REQUESTED_QUANTITY);
        final Cart cart = new Cart(new HashMap<>());
        final int supply_quantity = 100000;
        final int consume_quantity = 100000;


//        final int currentSamsungStock = baneasaMall.addItem(samsungS10, supply_quantity);
//        System.out.println("Initially Samsung S10 stock quantity: " + currentSamsungStock);

        final CartController cartController = new CartController(baneasaMall);

        final User anca = new User(new CartController(baneasaMall), samsungS10,AVAILABLE_QUANTITY);
        final User george = new User(new CartController(baneasaMall), samsungS10, AVAILABLE_QUANTITY);

        final Thread ancaThread = new Thread(anca);
        final Thread georgeThread = new Thread(george);
        ancaThread.start();
        georgeThread.start();

        ancaThread.join();
        georgeThread.join();

        System.out.println("Anca retrieved itmes quantity: " + anca.getRetrivedItemsQuantity());
        System.out.println("George retrieved itmes quantity: " + george.getRetrivedItemsQuantity());
        System.out.println("Stock itemes: " + baneasaMall.showItems());



//        int retrieveSamsungQuantity = baneasaMall.retrieveItem(samsungS10, 2);
//        System.out.println("Received Samsung S10 quantity2: " + retrieveSamsungQuantity);
//        System.out.println("Items to show: " + baneasaMall.showItems());
//        cart.addCartItem(samsungS10, 20);
//        cart.addCartItem(nike, 5);
//        try {
//            cart.printList();
//        } catch (IOException e) {
//            System.out.println("Canoot access file during print. " + e.getMessage());
//        }
//        baneasaMall.showItems();


    }
}
