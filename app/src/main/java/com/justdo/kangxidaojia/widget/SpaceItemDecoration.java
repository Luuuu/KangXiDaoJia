package com.justdo.kangxidaojia.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.util.Constants;

public class SpaceItemDecoration extends CustomRecycleView.ItemDecoration {

    private final Context mContext;
    private       int     space;
    public static final int ITEM_TYPE_HEADER = 1001;

    public SpaceItemDecoration(Context context) {
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int ishead = (int) parent.getTag();
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        int dimensionPixelSize = mContext.getResources().getDimensionPixelSize(R.dimen.px15);
        outRect.left = mContext.getResources().getDimensionPixelSize(R.dimen.px13) * 2;
        outRect.bottom = dimensionPixelSize;
        outRect.top = dimensionPixelSize;
        if (ishead != ITEM_TYPE_HEADER) {
            if (Constants.VIEW_PAGE) {
                if (childLayoutPosition % 2 == 0) {
                    outRect.top = mContext.getResources().getDimensionPixelSize(R.dimen.px12);
                }
                if (childLayoutPosition % 2 == 1) {
                    outRect.top = 0;
                }
            } else {
                if (childLayoutPosition % 2 == 1) {
                    outRect.top = mContext.getResources().getDimensionPixelSize(R.dimen.px12);
                    outRect.bottom = 0;
                }
                if (childLayoutPosition % 2 == 0) {
                    outRect.top = 0;
                }
            }
        } else if (childLayoutPosition != 0) {
            if (childLayoutPosition % 2 == 0) {
                outRect.bottom = 0;
            }
            if (childLayoutPosition % 2 == 1) {
                outRect.top = 0;
            }

        }
        if (childLayoutPosition == 0 && Constants.VIEW_PAGE) {
            outRect.left = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }else if (childLayoutPosition == 0 || childLayoutPosition == 1){
            if (!Constants.VIEW_PAGE) {
                outRect.left = 0;
            }
        }

    }

} 