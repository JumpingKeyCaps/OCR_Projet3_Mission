package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.openclassrooms.tajmahal.R;
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
 * Test the ViewModel of the reviews
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
    public void test_getReviews_callsRepository() {
        reviewsViewModel.getReviews();
        Mockito.verify(reviewsRepository).getReviews();
    }

    /**
     * Test addReview method call to repository
     */
    @Test
    public void test_addReview_callsRepository() {
        Review newReview = Mockito.mock(Review.class);
        Mockito.when(newReview.getComment()).thenReturn("This is a test review comment");
        Mockito.when(newReview.getRate()).thenReturn(3);
        try {
            reviewsViewModel.addReview(newReview);
        } catch (EmptyCommentaryException | EmptyRatingException | TooLongCommentaryException e) {
            throw new RuntimeException(e);
        }


        Mockito.verify(reviewsRepository).addReview(newReview);
    }

    /**
     * Test addReview method Empty Comment exception
     */
    @Test
    public void test_addReview_EmptyComment_Exception() {
        Review newReview = Mockito.mock(Review.class);
        Mockito.when(newReview.getComment()).thenReturn("");
        try {
            reviewsViewModel.addReview(newReview);
        } catch (Exception e) {
            assertTrue(e instanceof EmptyCommentaryException);
        }
    }
    /**
     * Test addReview method Empty rating exception
     */
    @Test
    public void test_addReview_EmptyRating_Exception() {
        Review newReview = Mockito.mock(Review.class);
        Mockito.when(newReview.getComment()).thenReturn("not an empty comment");
        Mockito.when(newReview.getRate()).thenReturn(0);
        try {
            reviewsViewModel.addReview(newReview);
        } catch (Exception e) {
            assertTrue(e instanceof EmptyRatingException);
        }
    }
    /**
     * Test addReview method TooLong commentary exception
     */
    @Test
    public void test_addReview_TooLongCommentary_Exception() {
        Review newReview = Mockito.mock(Review.class);
        Mockito.when(newReview.getComment()).thenReturn(
                " A very long comment with more than 255 char in it that will trigger the too long commentary exception." +
                        "A very long comment with more than 255 char in it that will trigger the too long commentary exception" +
                        "A very long comment with more than 255 char in it that will trigger the too long commentary exception");
        Mockito.when(newReview.getRate()).thenReturn(4);
        try {
            reviewsViewModel.addReview(newReview);
        } catch (Exception e) {
            assertTrue(e instanceof TooLongCommentaryException);
        }
    }


    /**
     * Test the string value returned by getReviewErrorMessage() method for the EmptyCommentaryException.
     */
    @Test
    public void getReviewErrorMessage_emptyCommentaryException_returnsReviewErrorNoTxtComment() {
        Exception exception = new EmptyCommentaryException();
        int errorMessage = reviewsViewModel.getReviewErrorMessage(exception);
        assertEquals(R.string.review_error_no_txt_comment, errorMessage);
    }

    /**
     * Test the string value returned by getReviewErrorMessage() method for the TooLongCommentaryException.
     */
    @Test
    public void getReviewErrorMessage_tooLongCommentaryException_returnsReviewErrorTxtTooLong() {
        Exception exception = new TooLongCommentaryException();
        int errorMessage = reviewsViewModel.getReviewErrorMessage(exception);
        assertEquals(R.string.review_error_txt_too_long, errorMessage);
    }
    /**
     * Test the string value returned by getReviewErrorMessage() method for the EmptyRatingException.
     */
    @Test
    public void getReviewErrorMessage_emptyRatingException_returnsReviewErrorNoRating() {
        Exception exception = new EmptyRatingException();
        int errorMessage = reviewsViewModel.getReviewErrorMessage(exception);
        assertEquals(R.string.review_error_no_rating, errorMessage);
    }
    /**
     * Test the string value returned by getReviewErrorMessage() method for all others exceptions.
     */
    @Test
    public void getReviewErrorMessage_otherException_returnsReviewErrorGeneric() {
        Exception exception = new Exception(); // Any other exception
        int errorMessage = reviewsViewModel.getReviewErrorMessage(exception);
        assertEquals(R.string.review_error_generic, errorMessage);
    }



}

