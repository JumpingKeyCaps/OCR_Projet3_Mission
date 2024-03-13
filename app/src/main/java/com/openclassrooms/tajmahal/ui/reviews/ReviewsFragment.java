package com.openclassrooms.tajmahal.ui.reviews;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentReviewsBinding;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.UserProfile;
import com.openclassrooms.tajmahal.ui.reviews.adapter.ItemReviewAdapter;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReviewsFragment extends Fragment {

    private FragmentReviewsBinding binding;
    private ReviewsViewModel ReviewsModel;
    private ItemReviewAdapter reviewAdapter;
    private UserProfile myUserProfile;
    private static final String USER_NAME = "Manon Garcia";
    private static final String USER_AVATAR_URL = "https://xsgames.co/randomusers/assets/avatars/female/23.jpg";
    final static int COMMENT_MAX_CHAR_ALLOWED = 255;

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

        //on creer un user profil, ceci sera a modifier lors de l'implementation du compte utilisateur
        myUserProfile = new UserProfile(USER_NAME,USER_AVATAR_URL);

        setupUI();
        setupViewModel();
        setupReviewsList();
        setupAddReviewUI();
        //on observe notre Livedata de la liste des reviews.
        ReviewsModel.getReviews().observe(requireActivity(), items -> {
            reviewAdapter.notifyDataSetChanged(); // on Maj notre adapter que la list a changer
        });
    }

    /**
     *  Setup the global UI settings like windows FLAGS and StatusBar.
     */
    private void setupUI() {
        Window window = requireActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     *  Setup the Recyclerview to display the reviews.
     */
    private void setupReviewsList(){
        reviewAdapter = new ItemReviewAdapter(ReviewsModel.getReviews().getValue());
        //on ajout notre layout manager : vertical avec l'affichage de la liste inverser.
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        binding.rvReviews.setLayoutManager(reviewsLayoutManager);
        binding.rvReviews.setAdapter(reviewAdapter);
    }

    /**
     *  Setup the UI of the user rating/comment feature.
     */
    private void setupAddReviewUI(){
        //lavatar de luser
        //todo to modifie later -----
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
        binding.buttonValiderComment.setOnClickListener(validateClickListener());


        /*

        todo  --- on defini un TextWatcher sur le edit text pour verifier la saisie. (????)

        binding.tvUserComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //   binding.buttonValiderComment.setEnabled(!charSequence.toString().isEmpty());
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
      */

    }

    /**
     * Initializes the ViewModel for the fragment.
     */
    private void setupViewModel() {
        //on recupere notre viewmodel via le provider
        ReviewsModel = new ViewModelProvider(this).get(ReviewsViewModel.class);
    }


    /**
     * OnClickListener to submit user review.
     *
     * @return a onClickListener for the validate button
     */
    private View.OnClickListener validateClickListener(){
        return view -> {
            //on verifie que le message n'est pas vide
            if(binding.tvUserComment.getText().toString().isEmpty()){  // ----------  on verifi que le commentaire comporte du text
                buttonErrorAnimation();
                Snackbar.make(view, R.string.review_error_no_txt_comment, Snackbar.LENGTH_SHORT).show();
            }else if(binding.rbUserRating.getRating()== 0f){  // ----------  on verifie que la note est mise
                buttonErrorAnimation();
                Snackbar.make(view, R.string.review_error_no_rating, Snackbar.LENGTH_SHORT).show();
            }else if(binding.tvUserComment.getText().length() > COMMENT_MAX_CHAR_ALLOWED){// ----------  on verifi que le text n'est pas trop long
                buttonErrorAnimation();
                Snackbar.make(view, R.string.review_error_txt_too_long, Snackbar.LENGTH_SHORT).show();
            }else{
                //all is ok to submit reviews
                // create a new review object
                final Review myReview = new Review(
                        myUserProfile.getUserName(),
                        myUserProfile.getUserAvatarURL(),
                        binding.tvUserComment.getText().toString(),
                        Math.round(binding.rbUserRating.getRating()));

                //ajout de la review a notre data layer.
                try{
                    //todo Methode 1:  direct via le livedata
                    //      ReviewsModel.getReviews().getValue().add(myReview);
                    //todo Methode 2:  asynch via le livedata
                    //      ReviewsModel.getReviews().postValue(new ArrayList<>(ReviewsModel.getReviews().getValue()).add(myReview));
                    //todo Methode 3: via addReview() methodes en cascades jusqu'a la data source.
                    //on utilise Objects.requireNonNull() pour parer un object vide.
                    ReviewsModel.addReview(Objects.requireNonNull(ReviewsModel.getReviews().getValue()).size(),myReview);

                    //maj du recycler via l'adapter
                    reviewAdapter.notifyItemInserted(ReviewsModel.getReviews().getValue().size());
                    //on clean le champs de saisie et le rating
                    binding.rbUserRating.setRating(0f);
                    binding.tvUserComment.getText().clear();
                }catch (NullPointerException e){
                    Snackbar.make(view, R.string.review_error_generic, Snackbar.LENGTH_SHORT).show();
                }
            }
            //on ferme le clavier si il est ouvert
            hideKeyboard(getActivity());
            //on clear le focus du edittext du commentaire
            binding.tvUserComment.clearFocus();
        };
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