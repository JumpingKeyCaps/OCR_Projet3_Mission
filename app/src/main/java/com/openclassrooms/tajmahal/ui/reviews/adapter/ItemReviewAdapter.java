package com.openclassrooms.tajmahal.ui.reviews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Reviews item adapter user by the Recyclerview of ReviewsFragment.
 * Use a ItemReviewViewHolder to display the reviews of the users.
 *
 */
public class ItemReviewAdapter extends RecyclerView.Adapter<ItemReviewViewHolder>  {

    private final List<Review> itemsReview;

    /**
     * Constructor to get an instance of this adapter.
     * @param reviewsList the list of review to display.
     */
    public ItemReviewAdapter(List<Review> reviewsList) {
        this.itemsReview = reviewsList;
    }

    /**
     * Create new ViewHolder to use with thos adapter.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public ItemReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ItemReviewViewHolder(view);
    }

    /**
     * Bind reviews data inside ViewHolder views.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ItemReviewViewHolder holder, int position) {
        Review CurrentReview = itemsReview.get(position);
        holder.reviewUserName.setText(CurrentReview.getUsername());
        holder.reviewUserCommentary.setText(CurrentReview.getComment());
        holder.reviewUserRating.setRating(CurrentReview.getRate());

        // Charger l'image de l'utilisateur via Picasso

        //  Picasso.get().load(CurrentReview.getPicture()).into(holder.reviewUserAvatar);

        //on la resize a une taille acceptable pour fluidifier le recyclerview
        //on place une image par dflt le temps du chargement, et la mm si une erreur est lever durant cette phase.
        Picasso.get().load(CurrentReview.getPicture())
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.reviewUserAvatar);
    }

    /**
     * Get the reviews total count.
     *
     * @return the number of reviews
     */
    @Override
    public int getItemCount() {
        return itemsReview.size();
    }



}
