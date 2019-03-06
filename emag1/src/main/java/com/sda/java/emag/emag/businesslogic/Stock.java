package com.sda.java.emag.emag.businesslogic;

import com.sda.java.emag.emag.item.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Stock implements Serializable {
    private final long serialVersionUID = 1L;

   private static final String SEPARATOR = " ";

    private Map<Item, Integer> items;
    private final transient Object mutex;

    public Stock(Map<Item, Integer> items, Object mutex) {
        this.items = items;
        this.mutex = mutex;
    }
    public int addItem(Item object, int supplyQuantity) {
        final Integer previousValue = items.putIfAbsent(object, supplyQuantity);
        if(previousValue == null){
            return supplyQuantity;
        }
        synchronized (mutex){
            final int supply = items.get(object) + supplyQuantity;
            items.put(object, supply);
            return supply;
        }
    }

    public  synchronized int retrieveItem(Item object, int requestedQuantity) {
        if (!items.containsKey(object)) {
            return 0;
        }
        int currentQuantity = items.get(object);
        if (currentQuantity < requestedQuantity) {
            items.put(object, 0);
            return currentQuantity;
        }
        int updatedQuantity = currentQuantity - requestedQuantity;
        items.put(object, updatedQuantity);
        return requestedQuantity;

    }

    public String showItems() {
        final StringBuilder displayResult = new StringBuilder();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            final Item item = itemEntry.getKey();
            displayResult.append(item.showDetails());
            displayResult.append(System.lineSeparator());
            displayResult.append(SEPARATOR);
            displayResult.append(itemEntry.getValue());
            displayResult.append(System.lineSeparator());
        }
        return displayResult.toString();
    }

    public void saveStock() throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream("StockSave");
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(this.toString());
        objectOutputStream.close();
        fileOutputStream.close();
        bufferedOutputStream.close();

    }

    public void loadStock() throws IOException, ClassNotFoundException {
        if (!Files.exists(Paths.get("StockSave"))) {
            return;
        }

        final FileInputStream fileInputStream = new FileInputStream("StockSave");
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        final Stock readObject = (Stock) objectInputStream.readObject();
        items = readObject.items;
        objectInputStream.close();
    }


}

