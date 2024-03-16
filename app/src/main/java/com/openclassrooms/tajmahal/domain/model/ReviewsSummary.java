package com.openclassrooms.tajmahal.domain.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.openclassrooms.tajmahal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents all restaurant review statistics.
 * This class encapsulates the stats of all users posted reviews, including the average rating of the reviewers,
 * the number of notes for each available note, and the total of reviews submitted.
 */
public class ReviewsSummary {
    /** The average rating of the restaurant. */
    private float averageScore;

    /** The number of reviews submitted about the restaurant. */
    private int totalReviewsCount;

    /** The Mapping :  number of reviews by available ratings .
     * for a Map<key,value> we have :
     *  - Key: Rating value.
     *  - Value: Number of reviews for this rating.
     * */
    private Map<Integer, Integer> numberOfReviewsForEachScore;


    /**
     * Note maximum and minimum allowed.
     */
    private static final int NOTE_MIN = 1;
    private static final int NOTE_MAX = 5;

    //---- Constructor

    /**
     * Construct a new ReviewsSummary instance.
     *
     * @param reviewsList the reviews list of the restaurant.
     */
    public ReviewsSummary(List<Review> reviewsList) {
        //Using in-class methods to do the data manipulation work directly inside the constructor.
        this.averageScore = calculateAverageRating(reviewsList);
        this.totalReviewsCount = reviewsList.size();
        this.numberOfReviewsForEachScore = getReviewCountByRating(reviewsList);
    }


    // ---- work methods

    /**
     * Calculate the number of reviews per rating for a list of reviews.
     *
     * @param reviews the list of reviews to process.
     * @return A Map containing the number of reviews for each note from 1 to 5.
     */
    public static Map<Integer, Integer> getReviewCountByRating(List<Review> reviews) {
        Map<Integer, Integer> numberReviewByRating = new HashMap<>();
        //Map our possible grades from 1 to 5
        for (int i = NOTE_MIN; i <= NOTE_MAX; i++) {
            numberReviewByRating.put(i, 0);
        }
        //For each review, we increment for each rating, the total of corresponding reviews.
        for (Review review : reviews) {
            int note = review.getRate();
            if (note >= NOTE_MIN && note <= NOTE_MAX) {
                numberReviewByRating.put(note, numberReviewByRating.get(note) + 1);
            }
        }
        return numberReviewByRating;
    }

    /**
     * Calculate the average rating of the restaurant.
     *
     * @param reviews the reviews list of the restaurant.
     * @return the average rating of the restaurant, between 1 to 5.
     */
    public float calculateAverageRating(List<Review> reviews){
        //check that the list is not empty
        if(reviews.isEmpty()) return 0.0f;
        //calculate the total score of all reviews
        float totalScore = 0.0f;
        for (Review review : reviews) {
            totalScore += review.getRate();
        }
        //returns the value of the average rounded to 1 decimal place after the decimal point.
        return Math.round((totalScore/reviews.size()) * 10) / 10f;
    }

    /**
     * Calculate the average rating. v2
     *<p>
     * maybe better ?
     * (use stream() and average() with default value)
     *</p>
     *
     * @param reviews the reviews list of the restaurant.
     * @return the average rating of the restaurant, between 1 to 5.
     */
    public float calculateAverageRatingV2(List<Review> reviews){
        //check that the list is not empty
        if(reviews.isEmpty()) return 0.0f;
        //returns the value of the average rounded to 1 decimal place after the decimal point, with a secure default value.
        return Math.round((reviews.stream().mapToInt(Review::getRate).average().orElse(0.0f)) * 10) / 10f;
    }


    /**
     * Forge a String via a text binding of total number reviews value .
     *
     * @param context a context to use getString().
     * @param totalReviewsCount the number of reviews of the restaurant.
     * @return a formatted String displaying the data.
     */
    public String totalNumberOfReviewsStringFormat(Context context, int totalReviewsCount){
        String reviewsCountInText;
        try{
            //Use of preformatted text resource.
            reviewsCountInText = context.getString(R.string.restaurant_total_reviews_format_txt,String.valueOf(totalReviewsCount));
        }catch (NullPointerException e){
            //do the binding manually in case of error
            reviewsCountInText = "("+totalReviewsCount+")";
        }
        return reviewsCountInText;
    }

    /**
     * Create a safe (with default value) list of String,
     * where the index is a rating, and the value is the number of reviews with this rating.
     *<p>
     * /!\ Warning for use the returned list: /!\
     * index start at 0 --> 1* rating
     *                1 --> 2* rating
     *                etc...
     *</p>
     *
     * @return a list of Integer representing the number of reviews for each rating.
     */
    public List<Integer> safeProgressRatingBarsValuesListGenerator(){
        List<Integer> progressOfRatingsBars = new ArrayList<>();
        for(int i=0;i<5;i++){
            int numberOfReviewsForThisRating;
            //retrieve the map of the reviews by note and create a list with default values, that is easier to handle.
            numberOfReviewsForThisRating = numberOfReviewsForEachScore.getOrDefault(i+1,0);
            progressOfRatingsBars.add(numberOfReviewsForThisRating);
        }
        return progressOfRatingsBars;
    }



    //---- Getters/Setters

    /**
     * Return the average rating of the restaurant.
     *
     * @return a float representing the average rating
     */
    public float getAverageScore() {
        return averageScore;
    }

    /**
     * Set or update the average rating of the restaurant.
     *
     * @param averageScore the new average rating
     */
    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }


    /**
     * Return the total number of reviews for the restaurant.
     *
     * @return a int representing the number of reviews.
     */
    public int getTotalReviewsCount() {
        return totalReviewsCount;
    }

    /**
     * Set or update the total number of reviews for the restaurant.
     *
     * @param totalReviewsCount the total of reviews submitted
     */
    public void setTotalReviewsCount(int totalReviewsCount) {
        this.totalReviewsCount = totalReviewsCount;
    }


    /**
     * Return the Map for reviews count by rating.
     *
     * @return a Map<Integer,Integer> representing the mapping of ratings/number of reviews
     */
    public @NonNull Map<Integer, Integer> getNumberOfReviewsForEachScore() {
        return numberOfReviewsForEachScore;
    }

    /**
     * Set or update the Map of number of reviews by available rating.
     *
     * @param numberOfReviewsForEachScore the Map<> of reviews count by rating
     */
    public void setNumberOfReviewsForEachScore(Map<Integer, Integer> numberOfReviewsForEachScore) {
        this.numberOfReviewsForEachScore = numberOfReviewsForEachScore;
    }



    //---- hashcode and equals

    /**
     * Compares this review summary with another object for equality.
     * Two reviews summary are considered equal if all their fields are identical.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsSummary that = (ReviewsSummary) o;
        return Float.compare(that.averageScore, averageScore) == 0 && totalReviewsCount == that.totalReviewsCount && Objects.equals(numberOfReviewsForEachScore, that.numberOfReviewsForEachScore);
    }

    /**
     * Generates a hash code for this review summary based on its fields.
     *
     * @return the generated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(averageScore, totalReviewsCount, numberOfReviewsForEachScore);
    }
}
