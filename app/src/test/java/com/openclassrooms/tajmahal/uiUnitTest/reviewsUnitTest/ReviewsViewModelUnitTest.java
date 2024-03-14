package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest;

import static junit.framework.TestCase.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;
import com.openclassrooms.tajmahal.ui.reviews.ReviewsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

/**
 * test the ViewModel of the reviews
 */
@RunWith(MockitoJUnitRunner.class)
public class ReviewsViewModelUnitTest {

    @Mock
    private ReviewsRepository reviewsRepository;

    /**
     * Mockito rule
     */
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private ReviewsViewModel reviewsViewModel;

    /**
     * Mockito setup
     */
    @Before
    public void setUp() {
        reviewsViewModel = new ReviewsViewModel(reviewsRepository);
    }

    /**
     * Test getReviews call to repository
     */
    @Test
    public void getReviews_callsRepository() {
        reviewsViewModel.getReviews();
        Mockito.verify(reviewsRepository).getReviews();
    }

    /**
     * Test addReview method call to repository
     */
    @Test
    public void addReview_callsRepository() {
        Review newReview = Mockito.mock(Review.class);
        reviewsViewModel.addReview(newReview);
        Mockito.verify(reviewsRepository).addReview(newReview);
    }

    /**
     * Test getReviewsSummary observer
     */
    @Test
    public void getReviewsSummary_observesReviews() {
        ReviewsSummary expectedSummary = new ReviewsSummary(4.0f, 3, new HashMap<>());

        // Mock LiveData with MutableLiveData
        MutableLiveData<ReviewsSummary> mockReviewsSummaryLiveData = new MutableLiveData<>();
        mockReviewsSummaryLiveData.setValue(expectedSummary); // Set initial value

        // Create a mock LifecycleOwner for testing
        LifecycleOwner lifecycleOwner = Mockito.mock(LifecycleOwner.class);
        Mockito.when(lifecycleOwner.getLifecycle()).thenReturn(LifecycleRegistry.createUnsafe(lifecycleOwner));


        // Observer to verify the emitted ReviewsSummary object
        Observer<ReviewsSummary> observer = reviewsSummary -> {
            assertEquals(expectedSummary.getAverageScore(), reviewsSummary.getAverageScore(), 0.01f);
            assertEquals(expectedSummary.getTotalReviewsCount(), reviewsSummary.getTotalReviewsCount());
        };

        (mockReviewsSummaryLiveData).observe(lifecycleOwner, observer);
    }
}

