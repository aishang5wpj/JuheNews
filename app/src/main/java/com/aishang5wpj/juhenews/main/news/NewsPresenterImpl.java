package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;
import com.aishang5wpj.juhenews.utils.FileUtils;
import com.google.gson.Gson;

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
    public void loadNews(NewsChannelBean.Channel channel, int page) {
        if (null == mNewsView) {
            return;
        }
        mNewsView.showProgress();
        mNewsModel.loadNews(channel, page, new INewsModel.OnLoadNewsListener() {
            @Override
            public void onLoadCompleted(final NewsBean newsBean) {

                mNewsView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mNewsView.onNewsLoad(newsBean);
                        mNewsView.hideProgress();
                    }
                });
            }
        });
    }

    @Override
    public void loadChannel() {
        if (null == mChannelView) {
            return;
        }

        String result = FileUtils.readAssertsFile(mChannelView.getContext(), "channel.json");
        NewsChannelBean newsChannelBean = new Gson().fromJson(result, NewsChannelBean.class);
        mChannelView.onLoadChannelCompleted(newsChannelBean);

        //从服务器拉去的新闻频道，请求新闻时好多都是302，是因为频繁请求被百度拒了？还是接口本身不可用了。
        if (true) {
            return;
        }
        mNewsModel.loadChannel(new INewsModel.OnLoadChannelListener() {
            @Override
            public void onLoadCompleted(final NewsChannelBean channelBean) {

                mChannelView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mChannelView.onLoadChannelCompleted(channelBean);
                    }
                });
            }
        });
    }
}
