package com.deanlib.bothwaylistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;


public abstract class BothwayListViewAdapter extends BaseAdapter
{
    private HeaderHorizontalScrollView mHeaderScrollView;

    public BothwayListViewAdapter(HeaderHorizontalScrollView headerScrollView)
    {
        super();
        this.mHeaderScrollView = headerScrollView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ConvertViewAndScrollView fullView = getFullView(position, convertView,
                parent);
        if (null != fullView.mItemScrollView)
        {
            mHeaderScrollView.addItemScrollView(fullView.mItemScrollView);
        }
        return fullView.mConvertView;
    }

    public abstract ConvertViewAndScrollView getFullView(int position,
            View convertView, ViewGroup parent);

    protected class ConvertViewAndScrollView
    {
        private View mConvertView;
        private HorizontalScrollView mItemScrollView;

        public ConvertViewAndScrollView(View convertView,
                HorizontalScrollView itemScrollView)
        {
            super();
            this.mConvertView = convertView;
            this.mItemScrollView = itemScrollView;
        }

    }
}
