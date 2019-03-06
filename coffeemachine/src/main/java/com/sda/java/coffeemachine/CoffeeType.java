package com.sda.java.coffeemachine;

public enum CoffeeType {

    ESPRESSO(5, 50, 0, 0),
    FILTERCOFFEE(5, 200, 0, 0),
    LATTE(3, 250, 0, 50);

    private int beansRequired;
    private int waterRequired;
    private int sugarRequired;
    private int milkRequired;

    CoffeeType(int coffee, int water, int sugar, int milk) {
        this.beansRequired = coffee;
        this.waterRequired = water;
        this.sugarRequired = sugar;
        this.milkRequired = milk;
    }

    public int getBeansRequired() {
        return beansRequired;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getSugarRequired() {
        return sugarRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }
}

