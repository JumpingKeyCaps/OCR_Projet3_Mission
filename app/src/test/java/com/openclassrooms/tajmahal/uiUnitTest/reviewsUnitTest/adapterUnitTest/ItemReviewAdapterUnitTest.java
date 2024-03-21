package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest.adapterUnitTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;



import com.openclassrooms.tajmahal.ui.reviews.adapter.ItemReviewAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Test of the Adapter for the RecyclerView of reviews fragment.
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemReviewAdapterUnitTest {


    /**
     * Test Constructor of the adapter
     */
    @Test
    public void constructor_ReviewsList() {
        //Act: create a new adapter instance
        ItemReviewAdapter adapter = new ItemReviewAdapter();
        // Assert: test if the adapter is well construct
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
        ItemReviewAdapter adapter = new ItemReviewAdapter();
        // Act
        int actualCount = adapter.getItemCount();
        // Assert
        assertEquals(0, actualCount);
    }

}
