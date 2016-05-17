package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsChannelBean;

/**
 * Created by wpj on 16/5/17上午10:05.
 */
public interface INewsPresenter {

    void loadNews(NewsChannelBean.Channel channel, int page);

    void loadChannel();
}
