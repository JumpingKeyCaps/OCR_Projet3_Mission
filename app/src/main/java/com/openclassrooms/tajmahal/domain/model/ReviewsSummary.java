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


    private static final int NOTE_MIN = 1;
    private static final int NOTE_MAX = 5;

    //---- Constructor

    /**
     * Construct a new ReviewsSummary instance.
     *
     * @param reviewsList the reviews list of the restaurant.
     */
    public ReviewsSummary(List<Review> reviewsList) {

        this.averageScore = calculateAverageRating(reviewsList);
        this.totalReviewsCount = reviewsList.size();
        this.numberOfReviewsForEachScore = getReviewCountByRating(reviewsList);
    }


    // ---- work methods


    /**
     * Calcule le nombre de notes par note pour une liste de reviews.
     *
     * @param reviews La liste des reviews.
     * @return Une Map contenant le nombre de notes pour chaque note de 1 à 5.
     */
    public static Map<Integer, Integer> getReviewCountByRating(List<Review> reviews) {
        Map<Integer, Integer> nombreNotesParNote = new HashMap<>();
        //on map nos notes possibles de 1 a 5
        for (int i = NOTE_MIN; i <= NOTE_MAX; i++) {
            nombreNotesParNote.put(i, 0);
        }
        //pour chaque review, on incremente pour chaque note, le total de reviews correspondantes.
        for (Review review : reviews) {
            int note = review.getRate();
            if (note >= NOTE_MIN && note <= NOTE_MAX) {
                nombreNotesParNote.put(note, nombreNotesParNote.get(note) + 1);
            }
        }
        return nombreNotesParNote;
    }

    /**
     * Calculate the average rating.
     *
     * @param reviews the reviews list.
     * @return a float with a average rate between 1 to 5.
     */
    public float calculateAverageRating(List<Review> reviews){
        //on check que la liste n'est pas vide
        if(reviews.isEmpty()) return 0.0f;
        //on calcul la note total de toutes les reviews
        float totalScore = 0.0f;
        for (Review review : reviews) {
            totalScore += review.getRate();
        }
        //on return la valeur de la moyenne arrondi a 1 chiffre apres la virgule
        return Math.round((totalScore/reviews.size()) * 10) / 10f;
    }

    /**
     * Calculate the average rating. V2
     *
     * maybe better ?
     * (use stream() and average() with default value)
     *
     * @param reviews the reviews list.
     * @return a float with a average rate between 1 to 5.
     */
    public float calculateAverageRatingV2(List<Review> reviews){
        //on check que la liste n'est pas vide
        if(reviews.isEmpty()) return 0.0f;
        //on return la valeur de la moyenne arrondie a 1 chiffre apres la virgule, avec une val par dflt.
        return Math.round((reviews.stream().mapToInt(Review::getRate).average().orElse(0.0f)) * 10) / 10f;
    }


    /**
     * Forge a String via a text binding of total number reviews value .
     *
     * @param context the context to use getString().
     * @return a formated String displaying the data.
     */
    public String totalNumberOfReviewsStringFormat(Context context, int totalReviewsCount){
        String reviewsCountInText;
        try{
            //getString peut generer une erreur si le context retourner par  getContext() est null
            reviewsCountInText = context.getString(R.string.restaurant_total_reviews_format_txt,String.valueOf(totalReviewsCount));
        }catch (NullPointerException e){
            //on fait le binding manuelement en cas derreur
            reviewsCountInText = "("+totalReviewsCount+")";
        }
        return reviewsCountInText;
    }

    /**
     *
     * create a  safe(with default value) list of String,
     * where index is a rate, and value the number of reviews with this rating.
     *
     * /!\ Warning for use the returned list: /!\
     * index start at 0 --> 1* rating
     *                1 --> 2* rating
     *                etc...
     *
     * @return a list of int representing the number of reviews for each rate.
     */
    public List<Integer> safeProgressRatingBarsValuesListGenerator(){
        List<Integer> progressOfRatingsBars = new ArrayList<>();
        for(int i=0;i<5;i++){
            int numberOfReviewsForThisRating;
            try{
                //on recupere la map de nos note par note et on creer une liste plus simple a manipuler avec des valeurs par default.
                numberOfReviewsForThisRating = numberOfReviewsForEachScore.getOrDefault(i+1,0);
            }catch (NullPointerException e){
                numberOfReviewsForThisRating = 0;
            }
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
