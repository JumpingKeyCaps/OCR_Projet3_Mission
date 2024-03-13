package com.openclassrooms.tajmahal.domain.modelUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.tajmahal.domain.model.Restaurant;

import org.junit.Test;

/**
 * Restaurant class unit tests.
 */
public class RestaurantUnitTest {

    /**
     * Test Constructor of the restaurant object.
     */
    @Test
    public void testRestaurantConstructor_withAllAttributes() {
        String name = "Taj Mahal";
        String type = "Indien";
        String hours = "11h30 - 14h30・18h30 - 22h00";
        String address = "12 Avenue de la Brique - 75010 Paris";
        String website = "http://www.tajmahal.fr";
        String phoneNumber = "06 12 34 56 78";
        boolean dineIn = true;
        boolean takeAway = true;

        Restaurant restaurant = new Restaurant(name, type, hours, address, website, phoneNumber, dineIn, takeAway);

        assertEquals(name, restaurant.getName());
        assertEquals(type, restaurant.getType());
        assertEquals(hours, restaurant.getHours());
        assertEquals(address, restaurant.getAddress());
        assertEquals(website, restaurant.getWebsite());
        assertEquals(phoneNumber, restaurant.getPhoneNumber());
        assertTrue(restaurant.isDineIn());
        assertTrue(restaurant.isTakeAway());
    }

    /**
     * Test all getter and setter of the restaurant object.
     */
    @Test
    public void testRestaurantGettersAndSetters() {
        Restaurant restaurant = new Restaurant("Initial Name", "Initial Type", "", "", "", "", false, false);

        String newName = "New Taj Mahal";
        String newType = "French";
        String newHours = "10h00 - 22h00";
        String newAddress = "1 Rue de la Paix - 75001 Paris";
        String newWebsite = "http://www.newtajmahal.fr";
        String newPhoneNumber = "01 23 45 67 89";
        boolean newDineIn = true;
        boolean newTakeAway = false;

        restaurant.setName(newName);
        restaurant.setType(newType);
        restaurant.setHours(newHours);
        restaurant.setAddress(newAddress);
        restaurant.setWebsite(newWebsite);
        restaurant.setPhoneNumber(newPhoneNumber);
        restaurant.setDineIn(newDineIn);
        restaurant.setTakeAway(newTakeAway);

        assertEquals(newName, restaurant.getName());
        assertEquals(newType, restaurant.getType());
        assertEquals(newHours, restaurant.getHours());
        assertEquals(newAddress, restaurant.getAddress());
        assertEquals(newWebsite, restaurant.getWebsite());
        assertEquals(newPhoneNumber, restaurant.getPhoneNumber());
        assertTrue(restaurant.isDineIn());
        assertFalse(restaurant.isTakeAway());
    }

    /**
     * Test equals null on restaurant objects.
     */
    @Test
    public void testRestaurantEquals_nullObject() {
        Restaurant restaurant = new Restaurant("Taj Mahal", "Indien", "", "", "", "", true, true);

        assertNotEquals(null, restaurant);
    }

    /**
     * Test equals on different restaurant objects.
     */
    @Test
    public void testRestaurantEquals_differentObject() {
        Restaurant restaurant1 = new Restaurant("Taj Mahal", "Indien", "", "", "", "", true, true);
        Restaurant restaurant2 = new Restaurant("Pizza Hut", "Italien", "", "", "", "", false, true);

        assertNotEquals(restaurant1, restaurant2);
    }
    /**
     * Test same values on restaurant objects.
     */
    @Test
    public void testRestaurantEquals_sameValues() {
        Restaurant restaurant1 = new Restaurant("Taj Mahal", "Indien", "11h30 - 14h30・18h30 - 22h00", "12 Avenue de la Brique - 75010 Paris", "http://www.tajmahal.fr", "06 12 34 56 78", true, true);
        Restaurant restaurant2 = new Restaurant("Taj Mahal", "Indien", "11h30 - 14h30・18h30 - 22h00", "12 Avenue de la Brique - 75010 Paris", "http://www.tajmahal.fr", "06 12 34 56 78", true, true);

        assertEquals(restaurant1, restaurant2);
    }

}
