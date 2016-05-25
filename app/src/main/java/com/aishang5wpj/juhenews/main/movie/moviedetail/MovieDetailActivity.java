package com.aishang5wpj.juhenews.main.movie.moviedetail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieDetailBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午2:21.
 */
public class MovieDetailActivity extends BaseActivity implements IMovieDetailView {

    public static final String MOVIE = "movie";
    private MovieBean.Movie mMovie;
    private IMovieDetailPresenter mMovieDetailPresenter;
    private ImageView mLogoIv;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private TextView mScoreTv, mVerTv, mDateTv, mCatTv, mDirTv, mStarTv, mDraTv;
    private RecyclerView mRecyclerView;
    private MovieDetailCommentAdapter mCommentAdapter;
    private int mPageIndex;
    private boolean mHasNext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }

    @Override
    public void onInitViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.movie_detail_pb);
        mLogoIv = (ImageView) findViewById(R.id.movie_detail_logo_iv);
        mScoreTv = (TextView) findViewById(R.id.movie_detail_score_tv);
        mVerTv = (TextView) findViewById(R.id.movie_detail_ver_tv);
        mDateTv = (TextView) findViewById(R.id.movie_detail_date_tv);
        mCatTv = (TextView) findViewById(R.id.movie_detail_cat_tv);
        mDirTv = (TextView) findViewById(R.id.movie_detail_dir_tv);
        mStarTv = (TextView) findViewById(R.id.movie_detail_star_tv);
        mDraTv = (TextView) findViewById(R.id.movie_detail_dra_tv);
        mRecyclerView = (RecyclerView) findViewById(R.id.movie_detail_comment_lv);
    }

    @Override
    public void onInitListeners() {

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
                    if (mHasNext) {

                        mMovieDetailPresenter.loadComments(mMovie, mPageIndex);
                    }
                }
            }
        });
    }

    @Override
    public void onInitData() {

        mMovie = (MovieBean.Movie) getIntent().getSerializableExtra(MOVIE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitle(mMovie.nm);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.movie_detail_ctl);
        collapsingToolbarLayout.setTitle(mMovie.nm);

        ImageUtils.getInstance().display(mLogoIv, mMovie.getImageUrl());

        mCommentAdapter = new MovieDetailCommentAdapter();
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRecyclerView.setAdapter(mCommentAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mMovieDetailPresenter = new MovieDetailPresenterImpl(this);
        mMovieDetailPresenter.loadMovieDetail(mMovie);
        mPageIndex = mMovieDetailPresenter.getFirstIndex();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onMovieDetailCompleted(MovieDetailBean movieDetailBean) {

        MovieDetailBean.MovieDetailModel model = movieDetailBean.data.MovieDetailModel;
        mScoreTv.setText(model.getScore());
        mVerTv.setText(model.ver);
        mDateTv.setText(model.rt);
        mCatTv.setText(String.format("类型：%s", model.cat));
        mDirTv.setText(String.format("导演：%s", model.dir));
        mStarTv.setText(String.format("主演：%s", model.star));
        mDraTv.setText(String.format("简介：%s", Html.fromHtml(model.dra)).trim());
        mCommentAdapter.setData(movieDetailBean.data.CommentResponseModel.hcmts);
    }

    @Override
    public void onLoadCommentCompleted(List<MovieDetailBean.Comment> commentList, boolean hasNext) {
        if (null != commentList) {
            mCommentAdapter.addData(commentList);
            mPageIndex++;
        }
        mHasNext = hasNext;
        if (!hasNext) {

            Snackbar.make(mRecyclerView, "后面没有了哦", Snackbar.LENGTH_SHORT).show();
        }
    }
}