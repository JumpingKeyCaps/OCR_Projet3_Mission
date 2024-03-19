package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * MainViewModel is responsible for preparing and managing the data for the {@link DetailsFragment}.
 * It communicates with the {@link RestaurantRepository} and {@link ReviewsRepository} to fetch restaurant details and Reviews.
 * Provides utility methods related to the restaurant UI and Rating UI.
 * This ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class DetailsViewModel extends ViewModel {
    /** The source repository for the restaurant details */
    private final RestaurantRepository restaurantRepository;
    /** The source repository for the  restaurant reviews details */
    private final ReviewsRepository reviewsRepository;


    /**
     * The LiveData to fetch and expose the restaurant list of reviews.
     * This liveData is initialised, his values will be added
     * by the call of the fetchReviews() public method from the fragment.
     */
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();

    /**
     *  The LiveData to "generate" and expose the ReviewsSummary object with the restaurant reviews details.
     *  <p>
     *  ReviewsSummary object is constructed by the transformation of the reviews livedata data.
     *  When the fragment call fetchReviews() method, reviews livedata add the data from the reviews repository.
     *  reviewSummary live data is "updated" by this data change, and create a new ReviewsSummary object with them.
     *  Then this new object is exposed with his new values to the fragment.
     *  </p>
     */
    final LiveData<ReviewsSummary> reviewsSummary = Transformations.map(reviews, input ->
            new ReviewsSummary(reviews.getValue())
    );


    /**
     * Constructor that Hilt will use to create an instance of MainViewModel.
     *
     * @param restaurantRepository The repository which will provide restaurant data.
     * @param reviewsRepository The repository which will provide reviews data.
     */
    @Inject
    public DetailsViewModel(RestaurantRepository restaurantRepository, ReviewsRepository reviewsRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewsRepository = reviewsRepository;
    }

    /**
     * Fetches the details of the Taj Mahal restaurant.
     *
     * @return LiveData object containing the details of the Taj Mahal restaurant.
     */
    public LiveData<Restaurant> getTajMahalRestaurant() {
        return restaurantRepository.getRestaurant();
    }

    /**
     * Retrieves the current day of the week in French.
     *
     * @return A string representing the current day of the week in French.
     */
    public String getCurrentDay(Context context) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString;

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dayString = context.getString(R.string.monday);
                break;
            case Calendar.TUESDAY:
                dayString = context.getString(R.string.tuesday);
                break;
            case Calendar.WEDNESDAY:
                dayString = context.getString(R.string.wednesday);
                break;
            case Calendar.THURSDAY:
                dayString = context.getString(R.string.thursday);
                break;
            case Calendar.FRIDAY:
                dayString = context.getString(R.string.friday);
                break;
            case Calendar.SATURDAY:
                dayString = context.getString(R.string.saturday);
                break;
            case Calendar.SUNDAY:
                dayString = context.getString(R.string.sunday);
                break;
            default:
                dayString = "";
        }
        return dayString;
    }

    /**
     * Methode to fetch all reviews from reviews repository.
     */
    public void fetchReviews(){
        //Set the value of the reviews livedata
        reviews.setValue(reviewsRepository.getReviews().getValue());

    }

}
