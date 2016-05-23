package com.aishang5wpj.juhenews.main.news.newsdetail;

import com.aishang5wpj.juhenews.bean.NewsDetailBean;

/**
 * Created by wpj on 16/5/21下午3:29.
 */
public interface INewsDetailView {

    void showMessage(String msg);

    void runOnUiThread(Runnable runnable);

    void showProgress();

    void hideProgress();

    void onNewsDetailLoadListener(NewsDetailBean newsDetailBean);
}
