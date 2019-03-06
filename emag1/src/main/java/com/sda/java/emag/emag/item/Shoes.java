package com.sda.java.emag.emag.item;

import java.util.Objects;

public class Shoes extends Item {

    private final String brand;
    private final int size;
    private final Color color;

    public Shoes(String name, float price, String brand, int size, Color color) {
        super(Category.FASHION, name, price);
        this.brand = brand;
        this.size = size;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String showDetails() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(brand);
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(size);
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(color);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return size == shoes.size &&
                Objects.equals(brand, shoes.brand) &&
                color == shoes.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, size, color);
    }
}
