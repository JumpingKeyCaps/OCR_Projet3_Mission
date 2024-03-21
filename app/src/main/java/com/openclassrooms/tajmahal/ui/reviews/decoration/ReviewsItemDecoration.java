package com.openclassrooms.tajmahal.ui.reviews.decoration;

import android.content.Context;
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
 * add a custom margin at Start and End to the line separator.
 */
public class ReviewsItemDecoration extends RecyclerView.ItemDecoration{

    private final static int MARGIN_LATERAL = 20;
    private final static float STROKE_WIDTH = 0.75f;
    private final Paint paint;
    private final int marginInDp;
    private final float strokeWidthInDp;

    /**
     * Constructor of the ItemDecoration
     *
     * @param context to get a reference to adapt the margin to the screen density and get the color to apply.
     */
    public ReviewsItemDecoration(Context context) {
        //Convert values to use in Dp
        marginInDp = (int) (MARGIN_LATERAL * context.getResources().getDisplayMetrics().density);
        strokeWidthInDp = (int) (STROKE_WIDTH * context.getResources().getDisplayMetrics().density);
        //Add color to the separator
        paint = new Paint();
        paint.setColor(context.getColor(R.color.itemview_separator_color));
        //Line stroke
        paint.setStrokeWidth(strokeWidthInDp);
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
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            //on se positionne pour dessiner
            int top = child.getBottom();
            int right = parent.getWidth() - marginInDp;
            float bottom = top + strokeWidthInDp; // Épaisseur du séparateur
            // Dessine le séparateur en bas de l'element.
            canvas.drawLine(marginInDp, top, right, bottom, paint);
        }
    }
}
