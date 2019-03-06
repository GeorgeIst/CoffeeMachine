package com.sda.java.emag.emag.item;

import com.sda.java.emag.emag.businesslogic.Displayble;

import java.io.Serializable;
import java.util.Objects;

public abstract class Item implements Displayble, Serializable {
    static final String SEPARATOR = " ";

    private final String name;
    private float price;
    private final Category category;

    Item(Category category, String name, float price) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemsDetails() {
        final StringBuilder displayResult = new StringBuilder();
        displayResult.append(name);
        displayResult.append(SEPARATOR);
        displayResult.append(price);
        displayResult.append(SEPARATOR);
        displayResult.append(category);
        return displayResult.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(item.price, price) == 0 &&
                Objects.equals(name, item.name) &&
                category == item.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }
}

