package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;

/**
 * Created by wpj on 16/5/17上午10:08.
 */
public interface INewsModel {

    void loadChannel(OnLoadChannelListener listener);

    void loadNews(NewsChannelBean.Channel channel, int page, OnLoadNewsListener listener);

    interface OnLoadChannelListener {
        void onLoadCompleted(NewsChannelBean channelBean);
    }

    interface OnLoadNewsListener {
        void onLoadCompleted(NewsBean newsBean);
    }
}
