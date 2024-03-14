package com.openclassrooms.tajmahal.dataUnitTest.serviceUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;

import org.junit.Test;

import java.util.List;

/**
 * RestaurantFakeApi unit tests class.
 */
public class RestaurantFakeApiUnitTest {


    /**
     * test if getRestaurant() return good values.
     */
    @Test
    public void testGetRestaurant_shouldReturnCorrectDetails() {
        RestaurantFakeApi api = new RestaurantFakeApi();
        Restaurant restaurant = api.getRestaurant();

        assertEquals("Taj Mahal", restaurant.getName());
        assertEquals("Indien", restaurant.getType());
        assertEquals("11h30 - 14h30・18h30 - 22h00", restaurant.getHours());
        assertEquals("12 Avenue de la Brique - 75010 Paris", restaurant.getAddress());
        assertEquals("http://www.tajmahal.fr", restaurant.getWebsite());
        assertEquals("06 12 34 56 78", restaurant.getPhoneNumber());
        assertTrue(restaurant.isDineIn());
        assertTrue(restaurant.isTakeAway());
    }

    /**
     * test if getRestaurant() return correct reviews list size.
     */
    @Test
    public void testGetReviews_shouldReturnCorrectSize() {
        RestaurantFakeApi api = new RestaurantFakeApi();
        List<Review> reviews = api.getReviews();

        assertEquals(7, reviews.size());
    }

    /**
     * test if getRestaurant() return correct first and last review.
     */
    @Test
    public void testGetReviews_shouldReturnFirstAndLastReview() {
        RestaurantFakeApi api = new RestaurantFakeApi();
        List<Review> reviews = api.getReviews();

        Review firstReview = reviews.get(0);
        Review lastReview = reviews.get(reviews.size()-1);

        assertEquals("Ranjit Singh", firstReview.getUsername());
        assertEquals("Alexa Warama", lastReview.getUsername());
    }

    /**
     * test addReview() to add a review in a list of reviews at start.
     */
    @Test
    public void testAddReview_shouldAddReviewAtStart() {
        RestaurantFakeApi api = new RestaurantFakeApi();
        Review newReview = new Review("John Doe", "pictureUrl", "Great food!",4);

        api.addReview(newReview);

        List<Review> reviews = api.getReviews();
        assertEquals(newReview, reviews.get(0));
    }
    /**
     * test addReview() to add a review in a list of reviews at End.
     */
    @Test
    public void testAddReview_shouldAddReviewAtEnd() {
        RestaurantFakeApi api = new RestaurantFakeApi();
        Review newReview = new Review("John Doe", "pictureUrl", "Great food!",4);

        api.addReview(newReview);

        List<Review> reviews = api.getReviews();
        assertEquals(newReview, reviews.get(reviews.size() - 1));
    }



}
