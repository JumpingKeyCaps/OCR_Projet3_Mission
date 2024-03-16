package com.openclassrooms.tajmahal.ui.reviews;


import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentReviewsBinding;
import com.openclassrooms.tajmahal.ui.reviews.decoration.ReviewsItemDecoration;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyCommentaryException;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.EmptyRatingException;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.exceptions.TooLongCommentaryException;
import com.openclassrooms.tajmahal.domain.model.UserProfile;
import com.openclassrooms.tajmahal.ui.reviews.adapter.ItemReviewAdapter;
import com.squareup.picasso.Picasso;


import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReviewsFragment extends Fragment {

    private FragmentReviewsBinding binding;
    private ReviewsViewModel reviewsModel;
    private ItemReviewAdapter reviewAdapter;


    /**
     * Get a new Instance of the fragment.
     *
     * @return a instance of ReviewsFragment
     */
    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    /**
     *  Create and inflate the fragment layout.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the root view layout of the fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    /**
     *  Called when all views was inflated and are now ready to use.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //on init nos elements
        setupViewModel();
        setupReviewsList();
        //on observe notre Livedata de la liste des reviews.
        reviewsModel.getReviews().observe(getViewLifecycleOwner(), items -> {
            reviewAdapter.UpdateReviewsList(items); // on Maj notre adapter que la list a changer
        });
        //on observe le LiveData du profile user
        reviewsModel.getUserProfile().observe(getViewLifecycleOwner(), this::setupAddReviewUI);
    }

    /**
     * Initializes the ViewModel for the fragment.
     */
    private void setupViewModel() {
        //on recupere notre viewmodel via le provider
        reviewsModel = new ViewModelProvider(this).get(ReviewsViewModel.class);
    }

    /**
     *  Setup the Recyclerview to display the reviews.
     */
    private void setupReviewsList(){

        reviewAdapter = new ItemReviewAdapter(reviewsModel.getReviews().getValue());
        //on ajout notre layout manager : vertical avec l'affichage de la liste inverser.
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        //views dividers --
        binding.rvReviews.addItemDecoration(new ReviewsItemDecoration(getResources()));

        binding.rvReviews.setLayoutManager(reviewsLayoutManager);
        binding.rvReviews.setAdapter(reviewAdapter);

    }

    /**
     *  Setup the UI of the user rating/comment feature.
     */
    private void setupAddReviewUI(UserProfile myUserProfile){
        //l'avatar de luser
        Picasso.get().load(myUserProfile.getUserAvatarURL())
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(binding.ivUserAvatar);

        //le rating de l'user par default
        binding.rbUserRating.setRating(0f);
        //le bouton retour arriere
        binding.goBackArrow.setOnClickListener(view -> requireActivity().onBackPressed());
        //le nom de l'user
        binding.tvUserName.setText(myUserProfile.getUserName());
        //on clear le focus du edittext du commentaire
        binding.tvUserComment.clearFocus();
        // on definie notre listener sur notre bouton valider
        binding.buttonValiderComment.setOnClickListener(validateClickListener(myUserProfile));

    }




    /**
     * OnClickListener to submit user review.
     *
     * @return a onClickListener for the validate button
     */
    private View.OnClickListener validateClickListener(UserProfile myUserProfile){
        return view -> {
            try{
                //via addReview() methodes en cascades jusqu'a la data source.
                reviewsModel.addReview(new Review(myUserProfile.getUserName(),myUserProfile.getUserAvatarURL(),binding.tvUserComment.getText().toString(),Math.round(binding.rbUserRating.getRating())));
                //maj du recycler via l'adapter
                reviewAdapter.notifyItemInserted(Objects.requireNonNull(reviewsModel.getReviews().getValue()).size());
                //on clean le champs de saisie et le rating
                binding.rbUserRating.setRating(0f);
                binding.tvUserComment.getText().clear();
            }catch (Exception e){
                buttonErrorAnimation();
                Snackbar.make(view,getReviewErrorMessage(e) , Snackbar.LENGTH_SHORT).show();
            }
            //on ferme le clavier si il est ouvert
            hideKeyboard(getActivity());
            //on clear le focus du edittext du commentaire
            binding.tvUserComment.clearFocus();

        };
    }

    private int getReviewErrorMessage(Exception e){
        if (e instanceof EmptyCommentaryException) {
            return R.string.review_error_no_txt_comment;
        } else if (e instanceof TooLongCommentaryException) {
            return R.string.review_error_txt_too_long;
        } else if (e instanceof EmptyRatingException) {
            return R.string.review_error_no_rating;
        } else {
            return R.string.review_error_generic; // Generic error message for unexpected exceptions
        }
    }


    /**
     *  Animate validate button when a error happen during review posting.
     */
    private void buttonErrorAnimation(){
        binding.buttonValiderComment.animate().cancel(); // on annule l'animation, avant dans lancer une nouvelle
        binding.buttonValiderComment.setTranslationX(-20f);
        binding.buttonValiderComment.animate().translationX(0f).setDuration(200).start();
    }

    /**
     * Hide the keyboard if open
     * @param activity  an activity reference to allow for system service call.
     */
    public static void hideKeyboard(Activity activity) {
        try {
            //on recupere le InputMethodemanager
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            //si au moin une vue a le focus actuel (ici notre Edittext)
            if (activity.getCurrentFocus() != null) {
                //on utilise hideSoftInputFromWindow() de l'InputMethodemanager pour fermer le clavier via le token de la vue qui a le focus.
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }catch (NullPointerException e){
            Log.e("KeyboardBehavior", "hideKeyboard: failed ! no visible keyboard found ");
        }
    }


}