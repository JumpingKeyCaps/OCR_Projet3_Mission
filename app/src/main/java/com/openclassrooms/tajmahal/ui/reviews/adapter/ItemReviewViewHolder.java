package com.openclassrooms.tajmahal.ui.reviews.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;

/**
 * ViewHolder used with {itemReviewAdapter}
 * representing the view used for each element to display
 * in the recyclerView, for an user reviews.
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
     * @param itemView the current line view root
     * */
    public ItemReviewViewHolder(View itemView) {
        super(itemView);
        reviewUserAvatar = itemView.findViewById(R.id.iv_ItemReview_UserAvatar);
        reviewUserName = itemView.findViewById(R.id.tv_ItemReview_UserName);
        reviewUserRating = itemView.findViewById(R.id.rb_ItemReview_UserRating);
        reviewUserCommentary = itemView.findViewById(R.id.tv_ItemReview_UserCommentary);
    }
}