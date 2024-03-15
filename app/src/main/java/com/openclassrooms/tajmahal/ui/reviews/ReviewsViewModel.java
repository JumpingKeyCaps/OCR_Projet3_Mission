package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.ui.reviews.reviewExceptions.EmptyCommentaryException;
import com.openclassrooms.tajmahal.ui.reviews.reviewExceptions.EmptyRatingException;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.reviewExceptions.TooLongCommentaryException;
import com.openclassrooms.tajmahal.ui.restaurant.DetailsFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * ReviewsViewModel is responsible for preparing and managing the data for the {@link DetailsFragment}.
 * and for the {@link }ReviewsFragment. It communicates with the {@link ReviewsRepository} to fetch reviews details and provides
 * utility methods related to the restaurant reviewing UI.
 * This ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class ReviewsViewModel extends ViewModel {

    private final ReviewsRepository reviewsRepository;

    final private static int COMMENT_MAX_CHAR_ALLOWED = 255;
    /**
     * Constructor that Hilt will use to create an instance of the ViewModel.
     *
     * @param reviewsRepository The repository which will provide reviews data.
     */
    @Inject
    public ReviewsViewModel(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;

    }

    /**
     * Fetches the list of reviews.
     *
     * @return List of reviews of the  restaurant.
     */
    public LiveData<List<Review>> getReviews() {
        return reviewsRepository.getReviews();
    }


    /**
     * add a new review to the restaurant.
     * <p>
     * This method will usually be connected to a network call or database query in its
     * implementing class, add a review to the list of the existing reviews.
     * </p>
     * @param newReview the new review to add
     */
    public void addReview(Review newReview) throws EmptyCommentaryException, EmptyRatingException, TooLongCommentaryException {
        //on verifie que le message n'est pas vide
        if(newReview.getComment().isEmpty()){  // ----------  on verifi que le commentaire comporte du text
            throw new EmptyCommentaryException();
        }else if(newReview.getRate() == 0f){  // ----------  on verifie que la note est mise
            throw new EmptyRatingException();
        }else if(newReview.getComment().length() > COMMENT_MAX_CHAR_ALLOWED){// ----------  on verifi que le text n'est pas trop long
            throw new TooLongCommentaryException();
        }else{
            reviewsRepository.addReview(newReview);
        }

    }







}