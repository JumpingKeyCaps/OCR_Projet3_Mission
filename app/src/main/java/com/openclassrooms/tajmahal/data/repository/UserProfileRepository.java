package com.openclassrooms.tajmahal.data.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.domain.model.UserProfile;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This is the repository class for managing UserProfile data.
 *
 */
@Singleton
public class UserProfileRepository {
    // The API interface instance that will be used for network requests related to User data.
    private final RestaurantApi restaurantApi;

    /**
     * Constructs a new instance of {@link UserProfileRepository} with the given {@link RestaurantApi}.
     *
     * @param restaurantApi The network API interface for fetching restaurant data.
     */
    @Inject
    public UserProfileRepository(RestaurantApi restaurantApi) {
        this.restaurantApi = restaurantApi;
    }

    /**
     * Fetches the User profile.
     * This method will make a network call using the provided {@link RestaurantApi} instance
     * to fetch all data of the current app user.
     *
     * @return LiveData holding the UserProfile data object.
     */
    public LiveData<UserProfile> getUserProfile(){
        return new MutableLiveData<>(restaurantApi.getUserProfile());
    }
}
