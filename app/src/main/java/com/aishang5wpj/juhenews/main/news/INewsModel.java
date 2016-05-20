package com.aishang5wpj.juhenews.main.news;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/17上午10:08.
 */
public interface INewsModel {

    void loadChannel(Context context, OnLoadChannelListener listener);

    void loadNews(NewsChannelBean channel, int page, OnLoadNewsListener listener);

    interface OnLoadChannelListener {
        void onLoadCompleted(List<NewsChannelBean> channelList);
    }

    interface OnLoadNewsListener {
        void onLoadCompleted(List<NewsBean> newsList);
    }
}
