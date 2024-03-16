package com.openclassrooms.tajmahal.domain.model;


import java.util.Objects;

/**
 * Represents a restaurant and its various attributes.
 * <p>
 * This class models a restaurant with its name, type (e.g., Indian, Italian), operational hours,
 * address, website, phone number, and availability of dine-in and take-away options.
 * </p>
 * <p>
 * For beginners: A class is a blueprint for creating objects in Java. Objects are instances of a class.
 * In this case, the class 'Restaurant' allows us to represent different restaurants with their specific details.
 * <p>
 * Example:
 * <pre>
 * Restaurant tajMahal = new Restaurant("Taj Mahal", "Indian", "11h30 - 22h00",
 *                                      "123 Street", "<a href="http://tajmahal.com">...</a>", "1234567890", true, false);
 * </pre>
 */
public class Restaurant {

    // Member variables representing attributes of a restaurant.
    private String name;
    private String type;
    private String hours;
    private String address;
    private String website;
    private String phoneNumber;
    private boolean dineIn;
    private boolean takeAway;

    /**
     * Constructor for the Restaurant class.
     *
     * @param name        The name of the restaurant.
     * @param type        The type or cuisine of the restaurant (e.g., Indian, Italian).
     * @param hours       The operational hours of the restaurant.
     * @param address     The address of the restaurant.
     * @param website     The website URL of the restaurant.
     * @param phoneNumber The contact phone number of the restaurant.
     * @param dineIn      A boolean indicating if dine-in is available.
     * @param takeAway    A boolean indicating if take-away service is available.
     */
    public Restaurant(String name, String type, String hours, String address, String website, String phoneNumber, boolean dineIn, boolean takeAway) {
        this.name = name;
        this.type = type;
        this.hours = hours;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.dineIn = dineIn;
        this.takeAway = takeAway;
    }

    /**
     * Returns the name of the restaurant.
     *
     * @return a String of the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set or update the name of the restaurant.
     *
     * @param name the new name of the restaurant
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the restaurant type.
     *
     * @return a String representing the type of restaurant
     */
    public String getType() {
        return type;
    }
    /**
     * Set or update the type of the restaurant.
     *
     * @param type the new type of the restaurant
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Returns the restaurant opening hours.
     *
     * @return a String representing the opening hours of the restaurant
     */
    public String getHours() {
        return hours;
    }
    /**
     * Set or update the hours of the restaurant.
     *
     * @param hours the new hours of the restaurant
     */
    public void setHours(String hours) {
        this.hours = hours;
    }
    /**
     * Returns the restaurant address.
     *
     * @return a String of the address of restaurant
     */
    public String getAddress() {
        return address;
    }
    /**
     * Set or update the address of the restaurant.
     *
     * @param address the new address of the restaurant
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns the restaurant website.
     *
     * @return a String representing the website of restaurant
     */
    public String getWebsite() {
        return website;
    }
    /**
     * Set or update the website of the restaurant.
     *
     * @param website the new website of the restaurant
     */
    public void setWebsite(String website) {
        this.website = website;
    }
    /**
     * Returns the restaurant phone number.
     *
     * @return a String representing the phone number of restaurant
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Set or update the phoneNumber of the restaurant.
     *
     * @param phoneNumber the new phoneNumber of the restaurant
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns if the restaurant have a dine in service .
     *
     * @return a boolean representing answer.
     */
    public boolean isDineIn() {
        return dineIn;
    }

    /**
     * Set if the restaurant have a dine in service.
     *
     * @param dineIn a boolean to enable/disable the service disponibility.
     */
    public void setDineIn(boolean dineIn) {
        this.dineIn = dineIn;
    }
    /**
     * Returns if the restaurant have a take away service.
     *
     * @return a boolean representing answer.
     */
    public boolean isTakeAway() {
        return takeAway;
    }
    /**
     * Set if the restaurant have a take away service.
     *
     * @param takeAway a boolean to enable/disable the service disponibility.
     */
    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }

    /**
     * Compares this restaurant with another object for equality.
     * Two restaurant are considered equal if all their fields are identical.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return dineIn == that.dineIn && takeAway == that.takeAway && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(hours, that.hours) && Objects.equals(address, that.address) && Objects.equals(website, that.website) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    /**
     * Generates a hash code for this restaurant based on its fields.
     *
     * @return the generated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, hours, address, website, phoneNumber, dineIn, takeAway);
    }

}
