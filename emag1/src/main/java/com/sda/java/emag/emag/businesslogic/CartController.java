package com.sda.java.emag.emag.businesslogic;

import com.sda.java.emag.emag.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartController {

    final private Cart cart = new Cart(new LinkedHashMap<>()); //only managed by the controller
    final private Stock stock;                                 //can be updated bya supplier, therefore the instance is injected

    public CartController(Stock stock) {
        this.stock = stock;
    }


    public int addItemToCart(Item item, int requestedQuantity) {
        final int retrievedItems = stock.retrieveItem(item, requestedQuantity);
        cart.addCartItem(item, retrievedItems);
        return retrievedItems;

    }

    public int removeItemFromCart(Item item, int removeItemQuantity) {
        final int remove = cart.removeItem(item, removeItemQuantity);
        return stock.addItem(item, remove);


    }

    public int removeAll() {
        final Map<Item, Integer> removeItems = cart.removeAll();
        for (Map.Entry<Item, Integer> entry : removeItems.entrySet()) {
            stock.addItem(entry.getKey(), entry.getValue());
        }
        return removeItems.entrySet().size();
    }
    public int removeAllWIthLambda() {
        final Map<Item, Integer> removeItems = cart.removeAll();
        removeItems.forEach(stock::addItem);//Not functional orientated programing
        return removeItems.entrySet().size();
    }
}
