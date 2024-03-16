package com.openclassrooms.tajmahal.ui.reviews;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;

import java.util.Objects;

/**
 * Item decoration to use with the RecyclerView of ReviewsFragment to separate itemReview.
 */
public class ReviewsItemDecoration extends RecyclerView.ItemDecoration{

     private final int MARGIN_LATERAL = 20;
    private final float STROKE_WIDTH = 0.75f;
    private final Paint paint;

    private final int marginInDp;
    private final float strokeWidthInDp;

    /**
     * Constructor of the ItemDecoration
     */
    public ReviewsItemDecoration(Resources resources) {
        marginInDp = (int) (MARGIN_LATERAL * resources.getDisplayMetrics().density);
        strokeWidthInDp = (int) (STROKE_WIDTH * resources.getDisplayMetrics().density);

        paint = new Paint();
        paint.setColor(resources.getColor(R.color.itemview_separator_color));
        paint.setStrokeWidth(strokeWidthInDp); // Épaisseur du séparateur
    }

    /**
     * Defining the margins around each item in the RecyclerView
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // Définissez la marge uniquement pour le bas de l'élément
        if (parent.getChildAdapterPosition(view) != Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1) {
            outRect.bottom = 0;
        }
    }

    /**
     * Method to draw the element on screen
     *
     * @param canvas Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state The current state of RecyclerView
     */
    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        //on parcours tout les elements et on ajoute notre separateur a chaque fois
        // Dessinez le séparateur en bas de chaque élément,
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int top = child.getBottom();
            int right = parent.getWidth() - marginInDp;
            float bottom = top + strokeWidthInDp; // Épaisseur du séparateur

            canvas.drawLine(marginInDp, top, right, bottom, paint);
        }
    }
}
