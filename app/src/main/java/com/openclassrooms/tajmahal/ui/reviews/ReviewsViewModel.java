package com.openclassrooms.tajmahal.ui.reviews;

import android.support.annotation.StringRes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.data.repository.UserProfileRepository;
import com.openclassrooms.tajmahal.domain.model.UserProfile;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyCommentaryException;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyRatingException;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.TooLongCommentaryException;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * ReviewsViewModel is responsible for preparing and managing the data for the {@link ReviewsFragment}.
 * It communicates with the {@link ReviewsRepository} to fetch reviews details and provides
 * utility methods related to the restaurant reviewing UI.
 * This ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class ReviewsViewModel extends ViewModel {

    private final ReviewsRepository reviewsRepository;
    private final UserProfileRepository userProfileRepository;
    final private static int COMMENT_MAX_CHAR_ALLOWED = 255;


    /**
     * Constructor that Hilt will use to create an instance of the ViewModel.
     *
     * @param reviewsRepository The repository which will provide reviews data.
     * @param userProfileRepository The repository which will provide User Profile data.
     */
    @Inject
    public ReviewsViewModel(ReviewsRepository reviewsRepository,UserProfileRepository userProfileRepository) {
        this.reviewsRepository = reviewsRepository;
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * Fetches the list of reviews.
     *
     * @return List of the restaurant reviews.
     */
    public LiveData<List<Review>> getReviews() {
        return reviewsRepository.getReviews();
    }

    /**
     * Add a new review to the restaurant.
     * <p>
     * This method will use some custom Exceptions to filter if the review
     * is ok to be add in reviews list.
     * </p>
     * @param newReview the new review object to add to the list of reviews
     */
    public void addReview(Review newReview) throws EmptyCommentaryException, EmptyRatingException, TooLongCommentaryException {
        //on verifie l'integriter de notre review a ajouter
        if(newReview.getComment().isEmpty()){  // ----------  on verifie que le message n'est pas vide
            throw new EmptyCommentaryException();
        }else if(newReview.getRate() == 0f){  // ----------  on verifie que la note est mise
            throw new EmptyRatingException();
        }else if(newReview.getComment().length() > COMMENT_MAX_CHAR_ALLOWED){// ----------  on verifi que le text n'est pas trop long
            throw new TooLongCommentaryException();
        }else{
            //All is ok,  on ajoute notre reviews a la liste des reviews.
            reviewsRepository.addReview(newReview);
        }
    }

    /**
     * Get the Error message to display to the user if adding a review fail.
     *
     * @param e the exception raised.
     * @return the message reference to display to the user
     */
    @StringRes
    public int getReviewErrorMessage(Exception e){
        if (e instanceof EmptyCommentaryException) {
            return R.string.review_error_no_txt_comment;
        } else if (e instanceof TooLongCommentaryException) {
            return R.string.review_error_txt_too_long;
        } else if (e instanceof EmptyRatingException) {
            return R.string.review_error_no_rating;
        } else {
            return R.string.review_error_generic; // Generic error message for unexpected exceptions
        }
    }

    /**
     * Fetches the details of the user profile.
     *
     * @return LiveData object containing the user profile object.
     */
    public LiveData<UserProfile> getUserProfile() {
        return userProfileRepository.getUserProfile();
    }




}