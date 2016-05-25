package com.aishang5wpj.juhenews.main.movie;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.MovieBean;

/**
 * Created by wpj on 16/5/25上午10:55.
 */
public class MovieFragment extends BaseFragment implements IMovieView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private int mPageIndex;
    private IMoviePresenter mMoviePresenter;
    private MovieAdapter mMovieAdapter;
    private boolean mHasNext;

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
                mPageIndex = mMoviePresenter.getStartIndex();
                mMoviePresenter.loadMovies(mPageIndex);
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
                    mMoviePresenter.loadMovies(mPageIndex);
                }
            }
        });
    }

    @Override
    public void onInitData() {
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieBean.Movie movie) {


            }
        });
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        mRecyclerView.setAdapter(mMovieAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mMoviePresenter = new MoviePresenterImpl(this);
        mPageIndex = mMoviePresenter.getStartIndex();
        mMoviePresenter.loadMovies(mPageIndex);
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

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void onLoadMoviesCompleted(MovieBean movieBean) {

        mHasNext = movieBean.data.hasNext;
        if (mPageIndex == mMoviePresenter.getStartIndex()) {

            mMovieAdapter.setData(movieBean.data.movies);
        } else {

            mMovieAdapter.addData(movieBean.data.movies);
        }
        mPageIndex++;
    }

    @Override
    public void showToast(String msg) {
        Snackbar.make(getActivity().getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }
}
