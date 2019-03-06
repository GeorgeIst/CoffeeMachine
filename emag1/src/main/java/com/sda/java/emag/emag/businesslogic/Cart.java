package com.sda.java.emag.emag.businesslogic;

import com.sda.java.emag.emag.item.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cart {
    static final String SEPARATOR = " ";

    private Map<Item, Integer> cartItems;
    private float total;

    public Cart(Map<Item, Integer> cartItems) {
        this.cartItems = cartItems;
    }


    public int addCartItem(Item item, int addQuantity) {
        total += addQuantity * item.getPrice();
        if (cartItems.containsKey(item)) {
            final int updateQuantity = cartItems.get(item) + addQuantity;
            cartItems.put(item, updateQuantity);
            return updateQuantity;
        }
        cartItems.put(item, addQuantity);
        return addQuantity;
    }

    public int removeItem(Item item, int removeQuantity) {
        if (!cartItems.containsKey(item)) {
            return 0;
        }
        int currentQuantity = cartItems.get(item);
        if (currentQuantity <= removeQuantity) {
            cartItems.remove(item);
            total -= currentQuantity * item.getPrice();
            return currentQuantity;
        }
        int updatedQuantity = currentQuantity - removeQuantity;
        cartItems.put(item, updatedQuantity);
        total -= currentQuantity * item.getPrice();
        return removeQuantity;

    }

    public Map<Item, Integer> removeAll() {
        final Map<Item, Integer> previousState = cartItems;
        cartItems = new HashMap<>();
        total = 0;
        return previousState;

    }

    public String checkout() {
        final String processedItems = removeAll().entrySet().stream()
                .map(itemEntry ->
                {
                    final StringBuilder displayResult = new StringBuilder();
                    final Item item = itemEntry.getKey();
                    displayResult.append(item.showDetails());
                    displayResult.append(SEPARATOR);
                    displayResult.append(itemEntry.getValue());
                    displayResult.append(System.lineSeparator());
                    return item.toString();
                }).collect(Collectors.joining(System.lineSeparator()));
        removeAll();
        return processedItems;

    }

    private String getStringCart() {
        return cartItems.entrySet().stream()
                .map(itemEntry ->
                {
                    final StringBuilder displayResult = new StringBuilder();
                    final Item item = itemEntry.getKey();
                    displayResult.append(item.showDetails());
                    displayResult.append(SEPARATOR);
                    displayResult.append(itemEntry.getValue());
                    displayResult.append(System.lineSeparator());
                    return item.showDetails();
                }).collect(Collectors.joining(System.lineSeparator()));
    }

    public void printList() throws IOException {

        final FileWriter fileWriter = new FileWriter("C:\\Users\\Picpalac\\IdeaProjects\\EMAG\\emag1\\src\\main\\resources\\Items_Cart.txt");
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        final String processedItems = getStringCart();
        bufferedWriter.write(processedItems);
        bufferedWriter.flush();
        bufferedWriter.close();

    }

}
