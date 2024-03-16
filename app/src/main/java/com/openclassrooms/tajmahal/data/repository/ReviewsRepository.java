package com.openclassrooms.tajmahal.data.repository;

import android.support.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This is the repository class for managing restaurant reviews.
 * <p>
 * Repositories are responsible for coordinating data operations from data sources such as network APIs, databases, etc.
 * Typically in an Android app built with architecture components, the repository will handle
 * the logic for deciding whether to fetch data from a network source or use data from a local cache.
 * <p>
 */
@Singleton
public class ReviewsRepository {
    // The API interface instance that will be used for network requests related to reviews data.
    private final RestaurantApi restaurantApi;

    /**
     * Constructs a new instance of {@link ReviewsRepository} with the given {@link RestaurantApi}.
     *
     * @param restaurantApi The network API interface for fetching reviews data.
     */
    @Inject
    public ReviewsRepository(RestaurantApi restaurantApi) {
        this.restaurantApi = restaurantApi;
    }

    /**
     * Fetches the reviews list.
     * This method will make a network call using the provided {@link RestaurantApi} instance
     * to fetch all reviews of the restaurant.
     *
     * @return  a LiveData holding the list of restaurant reviews.
     */
    public LiveData<List<Review>> getReviews(){
        return new MutableLiveData<>(restaurantApi.getReviews());
    }

    /**
     * Add a new review to the restaurant reviews list.
     * <p>
     * This method will usually be connected to a network call or database query in its
     * implementing class, to add a new review to the list of the existing reviews.
     * </p>
     * @param newReview the {@link Review} to add in the reviews list
     */
    public void addReview(@NonNull Review newReview){
        restaurantApi.addReview(newReview);
    }

}
