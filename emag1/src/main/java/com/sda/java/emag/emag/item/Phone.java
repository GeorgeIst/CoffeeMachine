package com.sda.java.emag.emag.item;

import java.util.Objects;

public class Phone extends Item {

    public static final Category CATEGORY = Category.ELECTRONICS;
    private final String brand;
    private final String cpu;
    private final float displaySize;

    public Phone(String name, float price, float displaySize, String brand, String cpu) {
        super(CATEGORY, name, price);
        this.displaySize = displaySize;
        this.brand = brand;
        this.cpu = cpu;
    }

    public String getBrand() {
        return brand;
    }

    public String getCpu() {
        return cpu;
    }

    public float getDisplaySize() {
        return displaySize;
    }


    @Override
    public String showDetails() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getItemsDetails());
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(brand);
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(cpu);
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(displaySize);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return Float.compare(phone.displaySize, displaySize) == 0 &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(cpu, phone.cpu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, cpu, displaySize);
    }
}

