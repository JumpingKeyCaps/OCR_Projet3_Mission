package com.openclassrooms.tajmahal.dataUnitTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

/**
 * Reviews Repository local unit test class.
 *
 */
public class ReviewsRepositoryUnitTest {
    // Simulate the RestaurantApi dependency
    private RestaurantApi mockRestaurantApi;

    // Instance of ReviewsRepository for testing
    private ReviewsRepository reviewsRepository;

    /**
     * Setup all mock stuff to test the class
     */
    @Before
    public void setUp() {
        // Create a mock RestaurantApi object via Mockito
        mockRestaurantApi = Mockito.mock(RestaurantApi.class);

        // Create a reviews repository with the mocked API
        reviewsRepository = new ReviewsRepository(mockRestaurantApi);
    }

    /**
     *  Test getReviews() method to see if a Mutable live data is returned and not null.
     */
    @Test
    public void testGetReviews_shouldReturnLiveData() {
        // Act : get the live data from the repository method
        LiveData<List<Review>> reviewsLiveData = reviewsRepository.getReviews();

        // Assert : Test if not null and if is a mutable live data
        assertNotNull(reviewsLiveData);
        assertTrue(reviewsLiveData instanceof MutableLiveData);
    }

    /**
     *  Test getReviews() method to see if Restaurant Api is called.
     */
    @Test
    public void testGetReviews_shouldCallRestaurantApi() {
        // Act: call restaurant api to get the livedata of reviews list
        reviewsRepository.getReviews();

        // Assert : check if restaurant api is called
        Mockito.verify(mockRestaurantApi).getReviews();
    }

    /**
     *  Test addReviews() method correct parameters call .
     */
    @Test
    public void testAddReview_shouldCallRestaurantApiWithCorrectParameters() {
        // Arrange: generate a review at index 5.
        int index = 5;
        Review newReview = new Review("John Doe", "UrlAvatar", "Great food!",4);

        // Act: add review to the list of reviews
        reviewsRepository.addReview(index, newReview);

        // Assert: check if review is added.
        Mockito.verify(mockRestaurantApi).addReview(index, newReview);
    }


}
