package com.openclassrooms.tajmahal.domain.modelUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ReviewSummary class unit tests.
 */
public class ReviewsSummaryUnitTest {

    /**
     * Test Constructor of the ReviewsSummary object.
     */
    @Test
    public void testConstructor() {
        //Act: add values to create a new ReviewsSummary object
        float averageScore = 4.2f;
        int totalReviewsCount = 100;
        Map<Integer, Integer> numberOfReviewsForEachScore = new HashMap<>();
        numberOfReviewsForEachScore.put(5, 20);
        numberOfReviewsForEachScore.put(4, 30);

        ReviewsSummary summary = new ReviewsSummary(averageScore, totalReviewsCount, numberOfReviewsForEachScore);

        //Assert: test if the object is well constructed
        assertNotNull(summary);
        assertEquals(averageScore, summary.getAverageScore(), 0.01f);
        assertEquals(totalReviewsCount, summary.getTotalReviewsCount());
        assertEquals(numberOfReviewsForEachScore, summary.getNumberOfReviewsForEachScore());
    }

    /**
     * Test Getter and Setter of the ReviewsSummary object.
     */
    @Test
    public void testGettersAndSetters() {
        //Act:  create a new ReviewsSummary object and set some values.
        ReviewsSummary summary = new ReviewsSummary(0.0f, 0, null);

        float newAverageScore = 3.5f;
        int newTotalReviewsCount = 50;
        Map<Integer, Integer> newNumberOfReviewsForEachScore = new HashMap<>();
        newNumberOfReviewsForEachScore.put(1, 10);

        summary.setAverageScore(newAverageScore);
        summary.setTotalReviewsCount(newTotalReviewsCount);
        summary.setNumberOfReviewsForEachScore(newNumberOfReviewsForEachScore);

        //Assert: test added values via the getters.
        assertEquals(newAverageScore, summary.getAverageScore(), 0.01f);
        assertEquals(newTotalReviewsCount, summary.getTotalReviewsCount());
        assertEquals(newNumberOfReviewsForEachScore, summary.getNumberOfReviewsForEachScore());
    }

    /**
     * Test Equality of the ReviewsSummary object.
     */
    @Test
    public void testEquals() {
        //Act: create 2 identical objects and 1 different.
        Map<Integer, Integer> ratings1 = new HashMap<>();
        ratings1.put(5, 10);
        ratings1.put(3, 5);
        ReviewsSummary summary1 = new ReviewsSummary(4.0f, 15, ratings1);

        Map<Integer, Integer> ratings2 = new HashMap<>();
        ratings2.put(5, 10);
        ratings2.put(3, 5);
        ReviewsSummary summary2 = new ReviewsSummary(4.0f, 15, ratings2);

        Map<Integer, Integer> ratings3 = new HashMap<>();
        ratings3.put(4, 20);
        ReviewsSummary summary3 = new ReviewsSummary(3.8f, 20, ratings3);

        //Assert: test equality result between objects
        assertEquals(summary1,summary2);
        assertNotEquals(summary1,summary3);
        assertNotEquals(null, summary1);
    }

    /**
     * Test HashCode of the ReviewsSummary object.
     */
    @Test
    public void testHashCode() {
        //Act: Create 2 map with identical data.
        Map<Integer, Integer> ratings1 = new HashMap<>();
        ratings1.put(5, 10);
        ratings1.put(3, 5);
        ReviewsSummary summary1 = new ReviewsSummary(4.0f, 15, ratings1);

        Map<Integer, Integer> ratings2 = new HashMap<>();
        ratings2.put(5, 10);
        ratings2.put(3, 5);
        ReviewsSummary summary2 = new ReviewsSummary(4.0f, 15, ratings2);

        //Assert: Equality hashcode of the 2 objects
        assertEquals(summary1.hashCode(), summary2.hashCode());
    }
}
