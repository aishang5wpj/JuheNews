package com.aishang5wpj.juhenews.main.news;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/17上午10:06.
 */
public class NewsPresenterImpl implements INewsPresenter {

    private INewsView mNewsView;
    private INewsModel mNewsModel;
    private INewsChannelView mChannelView;

    private NewsPresenterImpl() {
        mNewsModel = new NewsModelImpl();
    }

    public NewsPresenterImpl(INewsChannelView channelView) {
        this();
        mChannelView = channelView;
    }

    public NewsPresenterImpl(INewsView newsView) {
        this();
        mNewsView = newsView;
    }

    @Override
    public void loadNews(NewsChannelBean channel, int page) {
        if (null == mNewsView) {
            return;
        }
        mNewsView.showProgress();
        mNewsModel.loadNews(channel, page, new INewsModel.OnLoadNewsListener() {
            @Override
            public void onLoadCompleted(final List<NewsBean> newsList) {

                mNewsView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mNewsView.onNewsLoad(newsList);
                        mNewsView.hideProgress();
                    }
                });
            }
        });
    }

    @Override
    public void loadChannel(Context context) {
        if (null == mChannelView) {
            return;
        }
        mNewsModel.loadChannel(context, new INewsModel.OnLoadChannelListener() {
            @Override
            public void onLoadCompleted(final List<NewsChannelBean> channelList) {

                mChannelView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mChannelView.onLoadChannelCompleted(channelList);
                    }
                });
            }
        });
    }
}
