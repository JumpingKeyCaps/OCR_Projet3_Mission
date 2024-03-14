package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest.adapterUnitTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

import static org.mockito.Mockito.when;


import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.adapter.ItemReviewAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * Test of the Adapter for the RecyclerView of reviews fragment.
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemReviewAdapterUnitTest {

    @Mock
    private List<Review> reviewsList;

    /**
     * Test Constructor of the adapter
     */
    @Test
    public void constructor_setsReviewsList() {
        // Arrange : create a list of reviews
        reviewsList.add(new Review("","","",1));
        reviewsList.add(new Review("","","",1));
        reviewsList.add(new Review("","","",1));
        reviewsList.add(new Review("","","",1));

        //Act: create a new adapter instance with the list
        ItemReviewAdapter adapter = new ItemReviewAdapter(reviewsList);

        // Assert: test if the adapter is well construct
        assertEquals(reviewsList.size(), adapter.getItemCount());
        assertNotNull(adapter);
    }


    /**
     * Test the Binding methode
     */
    @Test
    public void testOnBindViewHolder() {

    }

    /**
     * Test the item count method
     */
    @Test
    public void getItemCount_returnsReviewsCount() {
        // Arrange
        int expectedCount = 3;
        when(reviewsList.size()).thenReturn(expectedCount);
        ItemReviewAdapter adapter = new ItemReviewAdapter(reviewsList);

        // Act
        int actualCount = adapter.getItemCount();

        // Assert
        assertEquals(expectedCount, actualCount);
    }



}
