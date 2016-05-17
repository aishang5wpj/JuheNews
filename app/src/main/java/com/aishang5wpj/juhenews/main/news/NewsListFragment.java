package com.aishang5wpj.juhenews.main.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class NewsListFragment extends BaseFragment implements INewsView {

    private static final String CHANNEL = "channel";
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private INewsPresenter mNewsPresenter;
    private NewsChannelBean.Channel mChannel;
    private NewsListAdapter mNewsListAdapter;

    public static NewsListFragment newInstance(NewsChannelBean.Channel channel) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle data = new Bundle();
        data.putSerializable(CHANNEL, channel);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void onInitViews() {

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.news_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
    }

    @Override
    public void onInitListeners() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mNewsPresenter.loadNews(mChannel, 0);
            }
        });
    }

    @Override
    public void onInitData() {

        mChannel = (NewsChannelBean.Channel) getArguments().getSerializable(CHANNEL);

        mNewsListAdapter = new NewsListAdapter();
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        mRecyclerView.setAdapter(mNewsListAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mNewsPresenter = new NewsPresenterImpl(this);
        mNewsPresenter.loadNews(mChannel, 1);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNewsLoad(NewsBean newsBean) {

        List<NewsBean.News> newsList = newsBean.showapi_res_body.pagebean.contentlist;
        mNewsListAdapter.setData(newsList);
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }
}
