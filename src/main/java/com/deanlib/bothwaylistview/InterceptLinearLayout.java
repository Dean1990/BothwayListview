package com.deanlib.bothwaylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class InterceptLinearLayout extends LinearLayout
{

    public InterceptLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public InterceptLinearLayout(Context context)
    {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return true;
    }

}
