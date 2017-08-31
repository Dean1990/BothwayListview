package com.deanlib.bothwaylistview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;


public class HeaderHorizontalScrollView extends HorizontalScrollView
{
    private Context mContext;
    private BroadcastReceiver mBroadcastReceiver;
    private final String RECEIVER_ACTION = "com.group_06.bothwaylistviewapi.widget.RECEIVER_ACTION";
    private ArrayList<HorizontalScrollView> mItemScrollViewList;

    public HeaderHorizontalScrollView(Context context, AttributeSet attrs,
            int defStyle)
    {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public HeaderHorizontalScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
    }

    public HeaderHorizontalScrollView(Context context)
    {
        super(context);
        this.mContext = context;
    }

    public void registerBrocadcastReceiver()
    {
        mItemScrollViewList = new ArrayList<HorizontalScrollView>();
        if (null == mBroadcastReceiver)
        {
            mBroadcastReceiver = new BroadcastReceiver()
            {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                    synchronized (mItemScrollViewList)
                    {
                        int size = mItemScrollViewList.size();
                        int l = intent.getIntExtra("l", -9999);
                        int t = intent.getIntExtra("t", -9999);
                        if (null != mItemScrollViewList && size > 0
                                && l != -9999 && t != -9999)
                        {
                            for (int i = 0; i < size; i++)
                            {
                                mItemScrollViewList.get(i).smoothScrollTo(l, t);
                            }
                        }
                    }
                }
            };
            IntentFilter filter = new IntentFilter();
            filter.addAction(RECEIVER_ACTION);
            mContext.registerReceiver(mBroadcastReceiver, filter);
        }
    }

    public void unRegediterBroadcastReceiver()
    {
        if (null != mBroadcastReceiver)
        {
            mContext.unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }

    public void addItemScrollView(HorizontalScrollView itemScrollView)
    {
        if (null != mItemScrollViewList
                && !mItemScrollViewList.contains(itemScrollView))
        {
            mItemScrollViewList.add(itemScrollView);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != mBroadcastReceiver)
        {
            Intent intent = new Intent();
            intent.putExtra("l", l);
            intent.putExtra("t", t);
            intent.setAction(RECEIVER_ACTION);
            mContext.sendBroadcast(intent);
        }
    }

}
