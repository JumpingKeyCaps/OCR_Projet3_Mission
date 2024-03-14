package com.openclassrooms.tajmahal.uiUnitTest.reviewsUnitTest.adapterUnitTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.ui.reviews.adapter.ItemReviewViewHolder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * test of the itemView ViewHolder of the reviews recyclerview/adapter
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemReviewViewHolderUnitTest {

    @Mock
    private View itemView;

    @Mock
    private ImageView reviewUserAvatar;

    @Mock
    private TextView reviewUserName;

    @Mock
    private RatingBar reviewUserRating;

    @Mock
    private TextView reviewUserCommentary;

    /**
     * Test the good inflation of the view holder
     */
    @Test
    public void constructor_inflatesViewsCorrectly() {
        // Arrange with mockito
        Mockito.when(itemView.findViewById(R.id.iv_ItemReview_UserAvatar)).thenReturn(reviewUserAvatar);
        Mockito.when(itemView.findViewById(R.id.tv_ItemReview_UserName)).thenReturn(reviewUserName);
        Mockito.when(itemView.findViewById(R.id.rb_ItemReview_UserRating)).thenReturn(reviewUserRating);
        Mockito.when(itemView.findViewById(R.id.tv_ItemReview_UserCommentary)).thenReturn(reviewUserCommentary);

        // Act : create a new ViewHolder
        ItemReviewViewHolder viewHolder = new ItemReviewViewHolder(itemView);

        // Assert: test views and View binding
        assertNotNull(viewHolder.reviewUserAvatar);
        assertNotNull(viewHolder.reviewUserName);
        assertNotNull(viewHolder.reviewUserRating);
        assertNotNull(viewHolder.reviewUserCommentary);

        verify(itemView).findViewById(R.id.iv_ItemReview_UserAvatar);
        verify(itemView).findViewById(R.id.tv_ItemReview_UserName);
        verify(itemView).findViewById(R.id.rb_ItemReview_UserRating);
        verify(itemView).findViewById(R.id.tv_ItemReview_UserCommentary);
    }

}
