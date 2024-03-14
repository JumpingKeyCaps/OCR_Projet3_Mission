package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest.utilityUnitTest;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;
import com.openclassrooms.tajmahal.ui.reviews.utility.ReviewsStatsUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test of the utility class for reviews stats.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReviewsStatsUtilsUnitTest {

    /**
     * Test the List returned by getReviewCountByRating() method
     */
    @Test
    public void testGetReviewCountByRating() {
        //Act: create a list of review and a map of expected results
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("","","",3));
        reviews.add(new Review("","","",2));
        reviews.add(new Review("","","",3));
        reviews.add(new Review("","","",1));
        reviews.add(new Review("","","",5));
        reviews.add(new Review("","","",5));

        Map<Integer, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put(1, 1);
        expectedCounts.put(2, 1);
        expectedCounts.put(3, 2);
        expectedCounts.put(4, 0);
        expectedCounts.put(5, 2);

        Map<Integer, Integer> actualCounts = ReviewsStatsUtils.getReviewCountByRating(reviews);

        //Assert: test is the map returned is equal to the expected map
        assertEquals(expectedCounts, actualCounts);
    }


    /**
     * Test Average rating job method
     */
    @Test
    public void testCalculateAverageRating() {
        //Act: Create a list of reviews with rating
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("","","",3));
        reviews.add(new Review("","","",2));
        reviews.add(new Review("","","",3));
        reviews.add(new Review("","","",4));

        float expectedAverage = 3.0f;
        float actualAverage = ReviewsStatsUtils.calculateAverageRating(reviews);
        //Assert: test if the good average rating is returned
        assertEquals(expectedAverage, actualAverage, 0.01f);

        // Test for empty list
        //Act: Create an empty list
        List<Review> emptyReviews = new ArrayList<>();
        float expectedEmptyAverage = 0.0f;
        float actualEmptyAverage = ReviewsStatsUtils.calculateAverageRating(emptyReviews);
        //Assert: test the average rating on empty list
        assertEquals(expectedEmptyAverage, actualEmptyAverage, 0.01f);
    }

    /**
     * Test the good string format for displaying total number of reviews
     */
    @Mock
    Context context;
    @Test
    public void testTotalNumberOfReviewsStringFormat() {
        //Act: create an expected String value for total of reviews.
        int totalReviewsCount = 123;
        String expectedReviewsCountInText = "(123)";
        // Mock getString() to return a specific value for testing
        Mockito.when(context.getString(R.string.restaurant_total_reviews_format_txt, String.valueOf(totalReviewsCount)))
                .thenReturn(expectedReviewsCountInText);
        String actualReviewsCountInText = ReviewsStatsUtils.totalNumberOfReviewsStringFormat(context, totalReviewsCount);

        //Assert: test value equality expected
        assertEquals(expectedReviewsCountInText, actualReviewsCountInText);
    }


    /**
     * Test the returned list of calculated values of reviews by rating
     */
    @Mock
    ReviewsSummary reviewsSummary;
    @Test
    public void testSafeProgressRatingBarsValuesListGenerator() {

        //Act: create a Map of reviews by rating
        // Mock the getNumberOfReviewsForEachScore() method to return specific values
        Map<Integer, Integer> numberOfReviewsForEachScore = new HashMap<>();
        numberOfReviewsForEachScore.put(1, 5);
        numberOfReviewsForEachScore.put(2, 10);
        numberOfReviewsForEachScore.put(3, 0);
        numberOfReviewsForEachScore.put(4, 8);
        numberOfReviewsForEachScore.put(5, 1);
        Mockito.when(reviewsSummary.getNumberOfReviewsForEachScore()).thenReturn(numberOfReviewsForEachScore);

        List<Integer> expectedProgressOfRatingsBars = Arrays.asList(5,10,0,8,1);
        List<Integer> actualProgressOfRatingsBars = ReviewsStatsUtils.safeProgressRatingBarsValuesListGenerator(reviewsSummary);

        //Assert: test returned result list equality with expected list
        assertEquals(expectedProgressOfRatingsBars, actualProgressOfRatingsBars);
    }

}
