package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;
import com.openclassrooms.tajmahal.ui.restaurant.DetailsFragment;
import com.openclassrooms.tajmahal.ui.reviews.utility.ReviewsStatsUtils;

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

    final private MutableLiveData<ReviewsSummary> reviewsSummaryLiveData = new MutableLiveData<>();


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
     * @param index position in list to add review
     * @param newReview the new review to add
     */
    public void addReview(int index, Review newReview){
        reviewsRepository.addReview(index,newReview);
    }


    /**
     * Fetches the Reviews summary .
     * @param lifecycleOwner le cycle owner
     * @return LiveData object containing an objet of Reviews Summary.
     */
    public LiveData<ReviewsSummary> getReviewsSummary(LifecycleOwner lifecycleOwner) {

        reviewsRepository.getReviews().observe( lifecycleOwner, reviews -> {
            // on creer notre objet ReviewsSummary via nos methods utilitaires et la list de reviews observer.
            final ReviewsSummary reviewsSummary = new ReviewsSummary(
                    ReviewsStatsUtils.calculateAverageRating(reviews),
                    reviews.size(),
                    ReviewsStatsUtils.getReviewCountByRating(reviews));
            // on update le live data associer si et seulement si les data on changer(upgrade efficiency si ViewModel est souvent observer) ou sont null.
            if(reviewsSummaryLiveData.getValue() == null || !reviewsSummary.equals(reviewsSummaryLiveData.getValue())){
                //on call l'update du live data via une methode dedier pour faciliter les tests/ et respecter le SRP
                updateReviewsSummary(reviewsSummary);
            }
        });

        return reviewsSummaryLiveData;
    }
    /**
     * Update Review summary LiveData .
     * @param reviewsSummary reviews summary objet to update
     */
    public void updateReviewsSummary(ReviewsSummary reviewsSummary) {
        reviewsSummaryLiveData.setValue(reviewsSummary);
    }
}