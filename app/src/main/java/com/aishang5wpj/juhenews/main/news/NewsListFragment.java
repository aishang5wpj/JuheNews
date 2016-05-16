package com.aishang5wpj.juhenews.main.news;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class NewsListFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void onInitViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        mRecyclerView.setAdapter(new NewsListAdapter());
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View v) {

    }
}
