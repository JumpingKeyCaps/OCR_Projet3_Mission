package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;
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
    public void addReview(Review newReview){
        reviewsRepository.addReview(newReview);
    }







}