package com.sda.java.emag.emag.businesslogic;

import com.sda.java.emag.emag.item.Item;

public class User implements Runnable {

    private static final long MILLISECONDS_TO_SECONDS_RATIO = 1000;
    private static final long MAX_RUN_TIME_SECONDS = 5;
    public static final int MAX_QUANTITY_PER_REQUESTED = 1;

    private final CartController cartController;

    private final Item requestedItem;
    private final int requestedQuantity;
    private int retrievedItemsQuantity;

    public User(CartController cartController, Item requestedItem, int requestedQuantity) {
        this.cartController = cartController;
        this.requestedItem = requestedItem;
        this.requestedQuantity = requestedQuantity;
    }

    private int getElapsedTimeInSeconds(long startTime) {
        return new Long((System.currentTimeMillis() - startTime) / MILLISECONDS_TO_SECONDS_RATIO).intValue();
    }


    @Override
    public void run() {
        retrievedItemsQuantity = 0;
        final long startTime = System.currentTimeMillis();
        while ((retrievedItemsQuantity < requestedQuantity) && (getElapsedTimeInSeconds(startTime) < MAX_RUN_TIME_SECONDS)) {
            retrievedItemsQuantity += cartController.addItemToCart(requestedItem, MAX_QUANTITY_PER_REQUESTED);

        }
    }

    public int getRetrivedItemsQuantity() {
        return retrievedItemsQuantity;
    }

}
