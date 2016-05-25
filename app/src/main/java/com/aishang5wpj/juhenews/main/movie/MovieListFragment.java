package com.aishang5wpj.juhenews.main.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieChannelBean;
import com.aishang5wpj.juhenews.main.picture.PictureDetailActivity;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午2:22.
 */
public class MovieListFragment extends BaseFragment implements IMovieView {

    private static final String MOVIE_CHANNEL = "pictureChannel";
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private IMoviePresenter mMoviePresenter;
    private MovieChannelBean mChannel;
    private MovieAdapter mMovieAdapter;
    private int mPageIndex;

    public static MovieListFragment newFragment(MovieChannelBean channel) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle data = new Bundle();
        data.putSerializable(MOVIE_CHANNEL, channel);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture_list;
    }

    @Override
    public void onInitViews() {

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.picture_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.picture_recycler_view);
    }

    @Override
    public void onInitListeners() {

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mChannel.isRefreshable()) {

                    mRefreshLayout.setRefreshing(false);
                    Snackbar.make(mRefreshLayout, mChannel.title + "不支持刷新哦", Snackbar.LENGTH_SHORT).show();
                } else {

                    mPageIndex = mMoviePresenter.getStartIndex();
                    mMoviePresenter.loadMovies(mChannel, mPageIndex);
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mMovieAdapter.getItemCount()) {

                    //加载更多
                    if (mChannel.isRefreshable()) {

                        mRefreshLayout.setRefreshing(true);
                        mMoviePresenter.loadMovies(mChannel, mPageIndex);
                    }
                }
            }
        });
    }

    @Override
    public void onInitData() {

        mChannel = (MovieChannelBean) getArguments().getSerializable(MOVIE_CHANNEL);

        //设置布局管理器
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieBean.Movie movie) {

                Intent intent = new Intent(getActivity(), PictureDetailActivity.class);
                intent.putExtra(PictureDetailActivity.PICTURE_URL, movie.getImageUrl());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mMovieAdapter);

        mMoviePresenter = new MoviePresenterImpl(this);
        mPageIndex = mMoviePresenter.getStartIndex();
        mMoviePresenter.loadMovies(mChannel, mPageIndex);
        mRefreshLayout.setRefreshing(true);
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
    public void runOnUiThread(Runnable runnable) {

        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void onMoviesLoadCompleted(List<MovieBean.Movie> MovieList) {

        mMovieAdapter.setData(MovieList);
        if (mMoviePresenter.getStartIndex() == mPageIndex) {

            mMovieAdapter.setData(MovieList);
        } else {

            mMovieAdapter.addData(MovieList);
        }
        mPageIndex++;
    }
}
