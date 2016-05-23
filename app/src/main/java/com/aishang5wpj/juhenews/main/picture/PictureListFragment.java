package com.aishang5wpj.juhenews.main.picture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.PictureBean;
import com.aishang5wpj.juhenews.bean.PictureChannelBean;
import com.aishang5wpj.juhenews.main.news.IPictureView;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午2:22.
 */
public class PictureListFragment extends BaseFragment implements IPictureView {

    /**
     * 页数从1开始
     */
    public static final int START_PAGE = 1;
    private static final String PICTURE_CHANNEL = "pictureChannel";
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private IPicturePresenter mPicturePresenter;
    private PictureChannelBean.Channel mChannel;
    private PictureAdapter mPictureAdapter;
    private int mPageIndex = START_PAGE;

    public static PictureListFragment newFragment(PictureChannelBean.Channel channel) {
        PictureListFragment fragment = new PictureListFragment();
        Bundle data = new Bundle();
        data.putSerializable(PICTURE_CHANNEL, channel);
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

                mPageIndex = START_PAGE;
                mPicturePresenter.loadPictures(mChannel, mPageIndex);
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
                        && lastVisibleItem + 1 == mPictureAdapter.getItemCount()) {

                    //加载更多
                    mRefreshLayout.setRefreshing(true);
                    mPicturePresenter.loadPictures(mChannel, mPageIndex);
                }
            }
        });
    }

    @Override
    public void onInitData() {

        mChannel = (PictureChannelBean.Channel) getArguments().getSerializable(PICTURE_CHANNEL);

        //设置布局管理器
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mPictureAdapter = new PictureAdapter();
        mPictureAdapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PictureBean.Picture picture) {

                Intent intent = new Intent(getActivity(), PictureDetailActivity.class);
                intent.putExtra(PictureDetailActivity.PICTURE, picture);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mPictureAdapter);

        mPicturePresenter = new PicturePresenterImpl(this);
        mPicturePresenter.loadPictures(mChannel, mPageIndex);
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
    public void onPicturesLoadCompleted(List<PictureBean.Picture> pictureList) {

        if (START_PAGE == mPageIndex) {

            mPictureAdapter.setData(pictureList);
        } else {

            mPictureAdapter.addData(pictureList);
        }
        mPageIndex++;
    }
}
