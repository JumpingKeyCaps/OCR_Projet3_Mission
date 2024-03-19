package com.openclassrooms.tajmahal.domain.modelUnitTest;

import static com.openclassrooms.tajmahal.domain.model.ReviewsSummary.getReviewCountByRating;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReviewSummary class unit tests.
 */
public class ReviewsSummaryUnitTest {
    /** Api used for the tests */
    RestaurantFakeApi api = new RestaurantFakeApi();
    List<Review> reviews = api.getReviews();

    /**
     * Test Constructor of the ReviewsSummary object.
     */
    @Test
    public void testConstructor() {
        //Act: add values to test a new ReviewsSummary object construction
        float resultAverageScore = 3.7f;
        int resultTotalReviewsCount = 7;
        Map<Integer, Integer> numberOfReviewsForEachScore = new HashMap<>();
        numberOfReviewsForEachScore.put(1, 1);
        numberOfReviewsForEachScore.put(2, 1);
        numberOfReviewsForEachScore.put(3, 0);
        numberOfReviewsForEachScore.put(4, 2);
        numberOfReviewsForEachScore.put(5, 3);

        ReviewsSummary summary = new ReviewsSummary(reviews);

        //Assert: test if the object is well constructed
        assertNotNull(summary);
        assertEquals(resultAverageScore, summary.getAverageScore(), 0.01f);
        assertEquals(resultTotalReviewsCount, summary.getTotalReviewsCount());
        assertEquals(numberOfReviewsForEachScore, summary.getNumberOfReviewsForEachScore());
    }

    /**
     * Test method getReviewCountByRating() in case where the list is empty.
     */
    @Test
    public void testGetReviewCountByRating_withEmptyList() {
        // Arrange (prepare test data)
        List<Review> emptyReviews = new ArrayList<>();

        // Act (call the method to test)
        Map<Integer, Integer> reviewCountByRating = getReviewCountByRating(emptyReviews);

        // Assert (verify the expected outcome)
        // We expect the map to contain all ratings from 1 to 5 with a count of 0
        for (int rating = 1; rating <= 5; rating++) {
            assertEquals(0, (int)reviewCountByRating.get(rating));
        }
    }

    /**
     * Test the good beahvior of the getReviewCountByRating() method.
     * per rating for a list of reviews.
     */
    @Test
    public void testGetReviewCountByRating_ClassicUse(){
        // Act
        Map<Integer, Integer> SimulatenumberOfReviewsForEachScore = new HashMap<>();
        SimulatenumberOfReviewsForEachScore.put(1, 1);
        SimulatenumberOfReviewsForEachScore.put(2, 1);
        SimulatenumberOfReviewsForEachScore.put(3, 0);
        SimulatenumberOfReviewsForEachScore.put(4, 2);
        SimulatenumberOfReviewsForEachScore.put(5, 3);
        //Assert
        assertEquals(SimulatenumberOfReviewsForEachScore, getReviewCountByRating(reviews));
    }


    /**
     * Test method calculateAverageRating() with empty list.
     */
    @Test
    public void testCalculateAverageRating_withEmptyList() {
        // Arrange (prepare test data)
        List<Review> emptyReviews = new ArrayList<>();
        ReviewsSummary summary = new ReviewsSummary(reviews);
        // Act (call the method to test)
        float averageRating = summary.calculateAverageRating(emptyReviews);
        float expectedAverage = 0.0f;
        // Assert (verify the expected outcome)
        assertEquals(expectedAverage, averageRating, 0.001f);
    }

    /**
     * Test method calculateAverageRating() in classic use.
     */
    @Test
    public void testCalculateAverageRating_withAllRatings() {
        // Arrange
        List<Review> testreviews = new ArrayList<>();
        testreviews.add(new Review("","","",3)); // Rate: 3
        testreviews.add(new Review("","","",5)); // Rate: 5
        testreviews.add(new Review("","","",4)); // Rate: 4
        ReviewsSummary summary = new ReviewsSummary(testreviews);
        // Act
        float averageRating = summary.calculateAverageRating(testreviews);
        float expectedAverage = 4.0f;
        // Assert
        assertEquals(expectedAverage, averageRating, 0.001f);
    }

    /**
     * Test method totalNumberOfReviewsStringFormat() to format a desired value. .
     */
    @Test
    public void testTotalNumberOfReviewsStringFormat_withResource() {
        // Arrange
        int totalReviewsCount = 15;
        String expectedReviewsCountInText = "(15)";

        // Mock the context to control the string resource retrieval
        Context mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.getString(R.string.restaurant_total_reviews_format_txt, String.valueOf(totalReviewsCount)))
                .thenReturn(expectedReviewsCountInText);
        ReviewsSummary summary = new ReviewsSummary(reviews);
        // Act
        String actualReviewsCountInText = summary.totalNumberOfReviewsStringFormat(mockContext, totalReviewsCount);

        // Assert
        assertEquals(expectedReviewsCountInText, actualReviewsCountInText);
    }

    /**
     * Test method safeProgressRatingBarsValuesListGenerator() with an empty map in parameter.
     */
    @Test
    public void testSafeProgressRatingBarsValuesListGenerator_withEmptyMap() {
        // Arrange
        // Mock (or create a test implementation) for numberOfReviewsForEachScore
        Map<Integer, Integer> mockReviewCountByRating = new HashMap<>();
        ReviewsSummary summary = new ReviewsSummary(reviews);
        summary.setNumberOfReviewsForEachScore(mockReviewCountByRating);
        // Act
        List<Integer> progressOfRatingsBars = summary.safeProgressRatingBarsValuesListGenerator();

        // Assert
        assertEquals(5, progressOfRatingsBars.size()); // Expect 5 elements (for ratings 1 to 5)
        for (int i = 0; i < 5; i++) {
            assertEquals(0, (int)progressOfRatingsBars.get(i)); // Expect default value (0) for each rating
        }
    }

    /**
     * Test method safeProgressRatingBarsValuesListGenerator() in classic use.
     */
    @Test
    public void testSafeProgressRatingBarsValuesListGenerator_withReviewCounts() {
        // Arrange
        Map<Integer, Integer> mockReviewCountByRating = new HashMap<>();
        mockReviewCountByRating.put(1, 2);
        mockReviewCountByRating.put(2, 5);
        mockReviewCountByRating.put(3, 1);
        mockReviewCountByRating.put(4, 0);
        mockReviewCountByRating.put(5, 4);
        ReviewsSummary summary = new ReviewsSummary(reviews);
        summary.setNumberOfReviewsForEachScore(mockReviewCountByRating);
        // Act
        List<Integer> progressOfRatingsBars = summary.safeProgressRatingBarsValuesListGenerator();

        // Assert
        assertEquals(5, progressOfRatingsBars.size());

        assertEquals(2, (int)progressOfRatingsBars.get(0));
        assertEquals(5, (int)progressOfRatingsBars.get(1));
        assertEquals(1, (int)progressOfRatingsBars.get(2));
        assertEquals(0, (int)progressOfRatingsBars.get(3));
        assertEquals(4, (int)progressOfRatingsBars.get(4));
    }


    /**
     * Test Getter and Setter of the ReviewsSummary object.
     */
    @Test
    public void testGettersAndSetters() {
        //Act:  create a new ReviewsSummary object and set some values.
        ReviewsSummary summary = new ReviewsSummary(reviews);

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


}
