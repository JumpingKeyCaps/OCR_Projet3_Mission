package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.data.repository.UserProfileRepository;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyCommentaryException;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyRatingException;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.TooLongCommentaryException;
import com.openclassrooms.tajmahal.ui.reviews.ReviewsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * test the ViewModel of the reviews
 */
@RunWith(MockitoJUnitRunner.class)
public class ReviewsViewModelUnitTest {

    @Mock
    private ReviewsRepository reviewsRepository;
    @Mock
    private UserProfileRepository userProfileRepository;

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
        reviewsViewModel = new ReviewsViewModel(reviewsRepository,userProfileRepository);
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

        try {
            reviewsViewModel.addReview(newReview);
        } catch (EmptyCommentaryException | EmptyRatingException | TooLongCommentaryException e) {
            throw new RuntimeException(e);
        }


        Mockito.verify(reviewsRepository).addReview(newReview);
    }

    /**
     * Test getReviewsSummary observer
     */
    @Test
    public void getReviewsSummary_observesReviews() {

    }
}

