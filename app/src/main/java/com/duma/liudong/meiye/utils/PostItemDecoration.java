package com.duma.liudong.meiye.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PostItemDecoration extends RecyclerView.ItemDecoration {

    private int space;


    public PostItemDecoration(int space) {
        this.space = space;
    }


    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);

//        if (spanSizeLookup.getSpanSize(position) == 1) {
        outRect.left = space;
        if (position % 2 == 0) {
            outRect.right = space;
        }
        outRect.bottom = 2 * space;
//        }
    }
}