package com.openclassrooms.tajmahal.ui.reviews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * The Adapter used to display each line of the restaurant reviews.
 *<p>
 * ItemReviewAdapter is used by the Recyclerview of ReviewsFragment.
 * Use a ItemReviewViewHolder to display the line representing the review of an user.
 *</p>
 */
public class ItemReviewAdapter extends RecyclerView.Adapter<ItemReviewViewHolder>  {

    /**The list of reviews to display */
    private  List<Review> itemsReview = new ArrayList<>();

    /**
     * Constructor to get an instance of this adapter.
     * no param -> the list of reviews will be inserted via UpdateReviewsList() method called from the fragment.
     */
    public ItemReviewAdapter() { }

    /**
     * Create new ViewHolder to use with this adapter.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the ItemReviewViewHolder to use to bind the list.
     */
    @NonNull
    @Override
    public ItemReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //On inflate le layout utiliser pour nos lignes d'avis
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
        //On recupere l'objet review a la position donner dans notre liste de reviews.
        Review CurrentReview = itemsReview.get(position);

        //On passe les datas au views correspondantes de notre holder
        holder.reviewUserName.setText(CurrentReview.getUsername());
        holder.reviewUserCommentary.setText(CurrentReview.getComment());
        holder.reviewUserRating.setRating(CurrentReview.getRate());

        // Chargement avec Picasso de l'avatar via son url
        Picasso.get().load(CurrentReview.getPicture())
                .resize(100, 100) //on la resize a une taille acceptable pour les perfomances du  recyclerview
                .centerCrop()
                .placeholder(R.drawable.logo) //on place une image par dflt le temps du chargement,
                .error(R.drawable.logo)  //idem si une erreur est lever durant cette phase.
                .into(holder.reviewUserAvatar); // on charge l'image dans la vue de notre holder
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

    /**
     * Update the reviewsList of the adapter.
     * <p>
     *      called by the fragment to update the adapter used list of reviews
     *      when the fragment observe a change in source data, he can update
     *      the RecyclerView displayed list via this method from the adapter.
     * </p>
     *
     * @param itemsReview the reviews updated list to use with the adapter.
     */
    public void updateReviewsList(List<Review> itemsReview){
        //On MAJ la list de reviews que l'adaptateur doit utiliser
        this.itemsReview = itemsReview;
        //On avertit  l'adaptateur qu'un changement a eu lieu a la dernier ligne de notre liste
        //(optimisation pour le cas de l'ajout d'un nouvel avi dans la liste)
        notifyItemInserted(getItemCount() - 1);
    }

}
