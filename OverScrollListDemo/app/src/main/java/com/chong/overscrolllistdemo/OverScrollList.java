package com.chong.overscrolllistdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * 参考来源 http://blog.csdn.net/harvic880925/article/details/48021931
 */
public class OverScrollList extends ListView {
    //定义最大滚动高度
    int mContentMaxMoveHeight = 300;

    public OverScrollList(Context context) {
        super(context);
    }

    public OverScrollList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverScrollList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 阻尼系数,越小阻力就越大.
     */
    public static final float SCROLL_RATIO = 0.25f;
    private Rect mHeadInitRect = new Rect();
    private View mTopView;

    public void setTopView(View view) {
        mTopView = view;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mHeadInitRect.set(mTopView.getLeft(), mTopView.getTop(), mTopView.getRight(), mTopView.getBottom());
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        System.out.println("deltaX======" + deltaX);
//        System.out.println("deltaY======" + deltaY);
//        System.out.println("scrollY======" + scrollY);
        //监听是否到底，如果到底就将maxOverScrollY设为0
        int newScrollY = scrollY + deltaY;
        final int bottom = maxOverScrollY + scrollRangeY;
        final int top = -maxOverScrollY;
        if (newScrollY > bottom) {
            maxOverScrollY = 0;
        } else if (newScrollY < top) {
            maxOverScrollY = mContentMaxMoveHeight;
        }
        //在向下移动时，scrollY是负值，所以scrollY + deltaY应该是当前应当所在位置。而由于scrollY + deltaY是负值，所以外层要包一个Math.abs（）来取绝对值
        int headerMoveHeight = (int) Math.abs((scrollY + deltaY) * SCROLL_RATIO);
        int mHeaderCurTop = mHeadInitRect.top + headerMoveHeight;
        mTopView.layout(mHeadInitRect.left, mHeaderCurTop, mHeadInitRect.right, mHeadInitRect.bottom + headerMoveHeight);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
