package com.whf.messagerelayer.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WHF on 2017/3/28.
 */

public class ContactListDecoration extends RecyclerView.ItemDecoration {

    private static final int WIDTH = 1;
    private static final int LINE_COLOR = 0xFFCCCACA;
    public ContactListDecoration() {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Paint paint = new Paint();
        paint.setColor(LINE_COLOR);
        int count = parent.getChildCount();
        for (int i = 0 ; i<count ; i++){
            View view = parent.getChildAt(i);
            int left = view.getLeft() + view.getPaddingLeft();
            int right = view.getRight() - view.getPaddingRight();
            RectF rectF = new RectF(left,view.getBottom(),right,view.getBottom()+WIDTH);
            c.drawRect(rectF,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = WIDTH;
    }
}
