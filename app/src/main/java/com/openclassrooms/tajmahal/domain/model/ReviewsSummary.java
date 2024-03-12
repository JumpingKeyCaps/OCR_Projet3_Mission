package com.openclassrooms.tajmahal.domain.model;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

/**
 * Represents all stats about the restaurant reviews.
 * This class encapsulates the stats of all users posted reviews, including the average rating of the reviewers,
 * the number of notes for each available note, and the total of reviews submitted.
 */
public class ReviewsSummary {
    /** The average rating of the restaurant. */
    private float averageScore;

    /** The number of reviews submitted to the restaurant. */
    private int totalReviewsCount;

    /** The Mapping :  number of rating by available ratings .
     * for a Map<key,value> we have :
     *  -Key: rating value.
     *  -value: number of review for this rating.
     * */
    private Map<Integer, Integer> numberOfReviewsForEachScore;


    //---- Constructor

    /**
     * Construct a new ReviewsSummary instance.
     *
     * @param averageScore the average rating of the restaurant.
     * @param totalReviewsCount  the total count of reviews.
     * @param numberOfReviewsForEachScore  the map of reviews count for each available rating.
     */
    public ReviewsSummary(float averageScore, int totalReviewsCount, Map<Integer, Integer> numberOfReviewsForEachScore) {
        this.averageScore = averageScore;
        this.totalReviewsCount = totalReviewsCount;
        this.numberOfReviewsForEachScore = numberOfReviewsForEachScore;
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
     * Return the total count of reviews.
     *
     * @return a int representing the number of reviews
     */
    public int getTotalReviewsCount() {
        return totalReviewsCount;
    }

    /**
     * Set or update the total count of reviews for the restaurant.
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
