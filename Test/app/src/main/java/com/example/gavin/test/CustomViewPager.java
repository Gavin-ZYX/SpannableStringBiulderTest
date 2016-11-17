package com.example.gavin.test;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gavin on 16/11/16.
 * 可以禁止左右滑动事件的Viewpager，默认可滑动
 */

public class CustomViewPager extends ViewPager {


    private boolean isPagingEnabled = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    /**
     * 设置viewpager是否可以滑动
     * @param b
     */
    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}