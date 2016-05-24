package com.aishang5wpj.juhenews.main.news.newsdetail;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wpj on 16/5/23上午11:38.
 */
public class NewsDetailImagesAdapter extends PagerAdapter {

    private List<ImageView> mSimpleDraweeViews;

    public NewsDetailImagesAdapter(List<ImageView> simpleDraweeViews) {
        mSimpleDraweeViews = simpleDraweeViews;
    }

    @Override
    public int getCount() {
        return mSimpleDraweeViews == null ? 0 : mSimpleDraweeViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(mSimpleDraweeViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(mSimpleDraweeViews.get(position));
        return mSimpleDraweeViews.get(position);
    }
}
