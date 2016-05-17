package com.aishang5wpj.juhenews.main.news;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.NewsChannelBean;

/**
 * Created by wpj on 16/5/17下午1:30.
 */
public interface INewsChannelView {

    void onLoadChannelCompleted(NewsChannelBean channelBean);

    void runOnUiThread(Runnable runnable);

    Context getContext();
}
