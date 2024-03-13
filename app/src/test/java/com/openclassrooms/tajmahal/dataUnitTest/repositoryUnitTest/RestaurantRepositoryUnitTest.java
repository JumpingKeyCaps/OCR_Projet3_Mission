package com.openclassrooms.tajmahal.dataUnitTest.repositoryUnitTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.domain.model.Restaurant;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Restaurant Repository local unit test class.
 *
 */
public class RestaurantRepositoryUnitTest {

    // Simulate the RestaurantApi dependency
    private RestaurantApi mockRestaurantApi;

    // Instance of ReviewsRepository for testing
    private RestaurantRepository restaurantRepository;

    /**
     * Setup all mock stuff to test the class
     */
    @Before
    public void setUp() {
        // Create a mock RestaurantApi object with mockito
        mockRestaurantApi = Mockito.mock(RestaurantApi.class);

        // Create a restaurant repository with the mocked API
        restaurantRepository = new RestaurantRepository(mockRestaurantApi);
    }

    /**
     *  Test getRestaurant() method to see if a Mutable live data is returned and not null.
     */
    @Test
    public void testGetRestaurant_shouldReturnLiveData() {
        // Act
        LiveData<Restaurant> restaurantLiveData = restaurantRepository.getRestaurant();

        // Assert
        assertNotNull(restaurantLiveData);
        assertTrue(restaurantLiveData instanceof MutableLiveData);
    }

    /**
     *  Test getRestaurant() method to see if Restaurant Api is called.
     */
    @Test
    public void testGetRestaurant_shouldCallRestaurantApi() {
        // Act
        restaurantRepository.getRestaurant();

        // Assert
        Mockito.verify(mockRestaurantApi).getRestaurant();
    }
}
