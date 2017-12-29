package com.ledou.mhw.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ledou.mhw.R;

/**
 * RecyclerView画分割线
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;
    public DividerGridItemDecoration(Context context)
    {    final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }  
  
    @Override  
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
        super.onDraw(c, parent, state);

    }  
  
    private int getSpanCount(RecyclerView parent)  
    {  
        // 列数  
        int spanCount = -1;  
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();  
        if (layoutManager instanceof GridLayoutManager)
        {  
  
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();  
        } else if (layoutManager instanceof StaggeredGridLayoutManager)  
        {  
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();  
        }  
        return spanCount;  
    }  
  
    public void drawHorizontal(Canvas c, RecyclerView parent)  
    {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)  
        {  
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child  
                    .getLayoutParams();  
            final int top = parent.getPaddingTop();  
            final int bottom = parent.getHeight() - parent.getPaddingBottom();  
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }  
    }  
  
    public void drawVertical(Canvas c, RecyclerView parent)  
    {  
        final int childCount = parent.getChildCount();  
        for (int i = 0; i < childCount; i++)  
        {  
            final View child = parent.getChildAt(i);  
  
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child  
                    .getLayoutParams();  
            final int left = parent.getPaddingLeft();  
            final int right = parent.getWidth() - parent.getPaddingRight();  
            final int top = child.getBottom() + params.bottomMargin +  
                    Math.round(ViewCompat.getTranslationY(child));  
            final int bottom = top + mDivider.getIntrinsicHeight();  
           mDivider.setBounds(left, top, right, bottom);  
            mDivider.draw(c);  
        }  
    }  
  
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,  
                                int childCount)  
    {  
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();  
        if (layoutManager instanceof GridLayoutManager)  
        {  
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边  
            {  
                return true;  
            }  
        } else if (layoutManager instanceof StaggeredGridLayoutManager)  
        {  
            int orientation = ((StaggeredGridLayoutManager) layoutManager)  
                    .getOrientation();  
            if (orientation == StaggeredGridLayoutManager.VERTICAL)  
            {  
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边  
                {  
                    return true;  
                }  
            } else  
            {  
                childCount = childCount - childCount % spanCount;  
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边  
                    return true;  
            }  
        }  
        return false;  
    }  
  
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,  
                              int childCount)  
    {  
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();  
        if (layoutManager instanceof GridLayoutManager)  
        {  
            childCount = childCount - childCount % spanCount;  
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部  
                return true;  
        } else if (layoutManager instanceof StaggeredGridLayoutManager)  
        {  
            int orientation = ((StaggeredGridLayoutManager) layoutManager)  
                    .getOrientation();  
            // StaggeredGridLayoutManager 且纵向滚动  
            if (orientation == StaggeredGridLayoutManager.VERTICAL)  
            {  
                childCount = childCount - childCount % spanCount;  
                // 如果是最后一行，则不需要绘制底部  
                if (pos >= childCount)  
                    return true;  
            } else  
            // StaggeredGridLayoutManager 且横向滚动  
            {  
                // 如果是最后一行，则不需要绘制底部  
                if ((pos + 1) % spanCount == 0)  
                {  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
  
    @Override  
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);  
        int spanCount = getSpanCount(parent);  
        int childCount = parent.getAdapter().getItemCount();  
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部  
        {  
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);  
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边  
        {  
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());  
        } else  
        {  
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),  
                    mDivider.getIntrinsicHeight());  
        }  
    }  
}