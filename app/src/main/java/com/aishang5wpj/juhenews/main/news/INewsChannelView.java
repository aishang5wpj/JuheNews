package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/17下午1:30.
 */
public interface INewsChannelView {

    void onLoadChannelCompleted(List<NewsChannelBean> channelList);

    void runOnUiThread(Runnable runnable);
}
