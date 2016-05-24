package com.aishang5wpj.juhenews.main.joke;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.JokeBean;
import com.aishang5wpj.juhenews.main.picture.PictureDetailActivity;

import java.util.List;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class JokeFragment extends BaseFragment implements IJokeView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private JokeAdapter mJokeAdapter;
    private IJokePresenter mJokePresenter;
    private int mPageIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_joke;
    }

    @Override
    public void onInitViews() {

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.joke_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.joke_lv);
    }

    @Override
    public void onInitListeners() {

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPageIndex = mJokePresenter.getStartIndex();
                mJokePresenter.loadJokes(mPageIndex);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {

                    //加载更多
                    mRefreshLayout.setRefreshing(true);
                    mJokePresenter.loadJokes(mPageIndex);
                }
            }
        });
    }

    @Override
    public void onInitData() {

        mJokeAdapter = new JokeAdapter();
        mJokeAdapter.setOnItemClickListener(new JokeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(JokeBean.Joke joke) {
                if (TextUtils.isEmpty(joke.picUrl)) {
                    return;
                }
                Intent intent = new Intent(getActivity(), PictureDetailActivity.class);
                intent.putExtra(PictureDetailActivity.PICTURE_URL, joke.picUrl);
                startActivity(intent);
            }
        });
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        mRecyclerView.setAdapter(mJokeAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mJokePresenter = new JokePresenterImpl(this);
        mPageIndex = mJokePresenter.getStartIndex();
        mJokePresenter.loadJokes(mPageIndex);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        if (null == getActivity()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
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
    public void onLoadCompleted(List<JokeBean.Joke> detail) {

        if (mPageIndex == mJokePresenter.getStartIndex()) {

            mJokeAdapter.setData(detail);
        } else {

            mJokeAdapter.addData(detail);
        }
        mPageIndex++;
    }
}
