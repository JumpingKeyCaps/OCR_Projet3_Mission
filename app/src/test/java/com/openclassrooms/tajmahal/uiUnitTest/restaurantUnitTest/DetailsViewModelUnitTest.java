package com.openclassrooms.tajmahal.uiUnitTest.restaurantUnitTest;

import static junit.framework.TestCase.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.ui.restaurant.DetailsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Test for the restaurant details view model
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsViewModelUnitTest {


    @Mock
    private RestaurantRepository mockRestaurantRepository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private DetailsViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new DetailsViewModel(mockRestaurantRepository);
    }

    /**
     * Test GetTajMahalRestaurant() method
     */
    @Test
    public void testGetTajMahalRestaurant_returnsLiveData() {
        // Simulate data access

        MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();
        restaurantLiveData.setValue(new Restaurant("Taj Mahal","","","","","",true,true));
        Mockito.when(mockRestaurantRepository.getRestaurant()).thenReturn(restaurantLiveData);

        // Get live data
        LiveData<Restaurant> restaurantLiveData2 = viewModel.getTajMahalRestaurant();

        // Assert data
        assertEquals(restaurantLiveData2, restaurantLiveData);
    }


    /**
     * Test of default value to getCurrentDay() method
     */
    @Test
    public void testGetCurrentDay_otherDay_returnsEmptyString() {
        // Mock context with string resources (doesn't matter which day)
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getString(Mockito.anyInt())).thenReturn("");

        String currentDay = viewModel.getCurrentDay(context);

        assertEquals("", currentDay);
    }


}
