package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsBean;

import java.util.List;

/**
 * Created by wpj on 16/5/17上午10:09.
 */
public interface INewsView {
    void showProgress();

    void hideProgress();

    void onNewsLoad(List<NewsBean> newsList);

    void runOnUiThread(Runnable runnable);
}
