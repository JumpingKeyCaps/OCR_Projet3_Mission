package com.openclassrooms.tajmahal.ui.reviews.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;

/**
 * ViewHolder used with {@link ItemReviewAdapter}
 * Hold the views used for each element to display
 * in the recyclerView, for an user review.
 *
 **/
public class ItemReviewViewHolder extends RecyclerView.ViewHolder {

    public ImageView reviewUserAvatar;
    public TextView reviewUserName;
    public RatingBar reviewUserRating;
    public TextView reviewUserCommentary;

    /**
     * Constructor of the holder.
     *
     * @param itemView the inflated line Layout to use.
     * */
    public ItemReviewViewHolder(View itemView) {
        super(itemView);
        reviewUserAvatar = itemView.findViewById(R.id.iv_ItemReview_UserAvatar);
        reviewUserName = itemView.findViewById(R.id.tv_ItemReview_UserName);
        reviewUserRating = itemView.findViewById(R.id.rb_ItemReview_UserRating);
        reviewUserCommentary = itemView.findViewById(R.id.tv_ItemReview_UserCommentary);
    }
}