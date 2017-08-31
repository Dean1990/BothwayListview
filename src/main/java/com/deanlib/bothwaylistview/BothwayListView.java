package com.deanlib.bothwaylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;


public class BothwayListView extends ListView
{

    private View mHeaderTopView;
    private HeaderHorizontalScrollView mHeaderScrollView;
    private TopScrollViewListener mScrollChangedListener;

    public BothwayListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public BothwayListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BothwayListView(Context context)
    {
        super(context);
    }

    public void initListView(View headerTopView,
            HeaderHorizontalScrollView headerScrollView)
    {
        this.setFocusable(true);
        this.setClickable(true);
        this.mScrollChangedListener = new TopScrollViewListener();
        this.setOnTouchListener(mScrollChangedListener);
        this.mHeaderTopView = headerTopView;
        this.mHeaderTopView.setFocusable(true);
        this.mHeaderTopView.setClickable(true);
        this.mHeaderTopView.setOnTouchListener(mScrollChangedListener);
        this.mHeaderScrollView = headerScrollView;
        this.mHeaderScrollView.registerBrocadcastReceiver();
    }

    public void unRegistererReceiver()
    {
        this.mHeaderScrollView.unRegediterBroadcastReceiver();
    }

    private class TopScrollViewListener implements OnTouchListener
    {

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if (null != mHeaderScrollView)
            {
                synchronized (mHeaderScrollView)
                {
                    try
                    {
                        mHeaderScrollView.onTouchEvent(event);
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
            return false;
        }
    }
}
