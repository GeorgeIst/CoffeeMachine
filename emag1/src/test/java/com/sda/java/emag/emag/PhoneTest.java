package com.sda.java.emag.emag;


import com.sda.java.emag.emag.item.Phone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PhoneTest {

    public static final String PHONE_NAME = "S10";
    public static final float PRICE = 1000f;
    public static final float DISPLAY_SIZE = 5.5f;
    public static final String BRAND = "Samsung";
    public static final String CPU = "Snapdragon855";
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;

    @Test
    public void itCreatesAPhone() {
        //Given

        //When
        final Phone samsung = new Phone(PHONE_NAME, PRICE, DISPLAY_SIZE, BRAND, CPU);

        //Then
        assertNotNull(samsung);
        assertEquals(PHONE_NAME, samsung.getName());
        assertEquals(PRICE, samsung.getPrice(), COMPARE_DOUBLE_DELTA);
        assertEquals(DISPLAY_SIZE, samsung.getDisplaySize(), COMPARE_DOUBLE_DELTA);
        assertEquals(BRAND, samsung.getBrand());
        assertEquals(CPU, samsung.getCpu());
        assertEquals(Phone.CATEGORY, samsung.getCategory());


    }

}