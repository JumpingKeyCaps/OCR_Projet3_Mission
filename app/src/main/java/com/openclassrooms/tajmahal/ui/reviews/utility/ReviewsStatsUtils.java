package com.openclassrooms.tajmahal.ui.reviews.utility;

import android.content.Context;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.ReviewsSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents all utility methods for generate/manage data to ReviewsSummary object
 * This class encapsulates methods used to generate/calculate reviews global stats .
 * from the list of reviews to a ReviewsSummary object.
 * and some methods to help displaying format.
 */
public class ReviewsStatsUtils {

    private static final int NOTE_MIN = 1;
    private static final int NOTE_MAX = 5;


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
    public static float calculateAverageRating(List<Review> reviews){
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
    public static float calculateAverageRatingV2(List<Review> reviews){
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
    public static String totalNumberOfReviewsStringFormat(Context context,int totalReviewsCount){
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
     * @param reviewsSummary the object with reviews data.
     * @return a list of int representing the number of reviews for each rate.
     */
    public static List<Integer> safeProgressRatingBarsValuesListGenerator(ReviewsSummary reviewsSummary){
        List<Integer> progressOfRatingsBars = new ArrayList<>();
        for(int i=0;i<5;i++){
            int numberOfReviewsForThisRating;
            try{
                //on recupere la map de nos note par note et on creer une liste plus simple a manipuler avec des valeurs par default.
                numberOfReviewsForThisRating = reviewsSummary.getNumberOfReviewsForEachScore().getOrDefault(i+1,0);
            }catch (NullPointerException e){
                numberOfReviewsForThisRating = 0;
            }
            progressOfRatingsBars.add(numberOfReviewsForThisRating);
        }
        return progressOfRatingsBars;
    }

}
