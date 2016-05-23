package com.aishang5wpj.juhenews.main.news.newsdetail;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;

/**
 * Created by wpj on 16/5/21下午3:29.
 */
public interface INewsDetailModel {

    void loadNewsDetail(NewsBean newsBean, OnNewsDetailLoadListener loadListener);

    interface OnNewsDetailLoadListener {
        void onLoadComplete(NewsDetailBean detailBean);

        void onLoadFailed();
    }
}
