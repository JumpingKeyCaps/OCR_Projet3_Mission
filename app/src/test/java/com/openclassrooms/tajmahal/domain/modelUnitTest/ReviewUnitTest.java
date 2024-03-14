package com.openclassrooms.tajmahal.domain.modelUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.openclassrooms.tajmahal.domain.model.Review;

import org.junit.Test;

/**
 * Review class unit tests.
 */
public class ReviewUnitTest {


    /**
     * Test Constructor of the review object.
     */
    @Test
    public void testConstructor() {
        //Act: create a new review object
        String username = "John Doe";
        String picture = "https://test.test/profile.jpg";
        String comment = "Great restaurant!";
        int rate = 5;

        Review review = new Review(username, picture, comment, rate);

        // Assert : test objet is well created
        assertNotNull(review);
        assertEquals(username, review.getUsername());
        assertEquals(picture, review.getPicture());
        assertEquals(comment, review.getComment());
        assertEquals(rate, review.getRate());
    }

    /**
     * Test getter and setter of the review object.
     */
    @Test
    public void testGettersAndSetters() {
        //Act: create a new review object
        Review review = new Review("testUser", "", "", 0);

        //value to change by
        String newUsername = "NewUser";
        String newPicture = "https://example.com/new_profile.jpg";
        String newComment = "This is a new comment";
        int newRate = 3;

        //setting values
        review.setUsername(newUsername);
        review.setPicture(newPicture);
        review.setComment(newComment);
        review.setRate(newRate);

        //Assert: test if setter and getter work well
        assertEquals(newUsername, review.getUsername());
        assertEquals(newPicture, review.getPicture());
        assertEquals(newComment, review.getComment());
        assertEquals(newRate, review.getRate());
    }

    /**
     * Test equality method of the review object.
     */
    @Test
    public void testEquals() {
        //Act : create 2 same reviews, and 1 different.
        Review review1 = new Review("user1", "pic1", "comment1", 4);
        Review review2 = new Review("user1", "pic1", "comment1", 4);
        Review review3 = new Review("user2", "pic2", "comment2", 3);

        //Assert: test equality result
        assertEquals(review1, review2);
        assertNotEquals(review1, review3);
        assertNotEquals(null, review1);
    }

    /**
     * Test hash method of the review object.
     */
    @Test
    public void testHashCode() {
        //Act: create 2 times an object with the same params.
        Review review1 = new Review("user1", "pic1", "comment1", 4);
        Review review2 = new Review("user1", "pic1", "comment1", 4);
        //Assert:  test  hashCode equality of the 2 objects.
        assertEquals(review1.hashCode(), review2.hashCode());
    }

}
