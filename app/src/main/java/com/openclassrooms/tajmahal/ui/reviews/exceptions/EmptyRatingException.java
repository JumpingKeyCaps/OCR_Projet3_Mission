package com.openclassrooms.tajmahal.ui.reviews.exceptions;

/**
 *  Custom Exception class to manage a review without a rating.
 */
public class EmptyRatingException extends Exception {
    public EmptyRatingException(){
        super();
    }
}
