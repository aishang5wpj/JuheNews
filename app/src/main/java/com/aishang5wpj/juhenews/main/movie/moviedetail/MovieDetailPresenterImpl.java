package com.aishang5wpj.juhenews.main.movie.moviedetail;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieDetailBean;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午2:56.
 */
public class MovieDetailPresenterImpl implements IMovieDetailPresenter {

    private IMovieDetailView mMovieDetailView;
    private IMovieDetailModel mMovieDetailModel;

    public MovieDetailPresenterImpl(IMovieDetailView movieDetailView) {
        mMovieDetailView = movieDetailView;
        mMovieDetailModel = new MovieDetailModelImpl();
    }

    @Override
    public void loadMovieDetail(MovieBean.Movie movie) {

        mMovieDetailView.showProgress();
        mMovieDetailModel.loadMovieDetal(movie, new IMovieDetailModel.OnLoadMovieDetalListener() {
            @Override
            public void onLoadFailed() {
                mMovieDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieDetailView.hideProgress();
                    }
                });
            }

            @Override
            public void onLoadCompleted(final MovieDetailBean movieDetailBean) {

                mMovieDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieDetailView.hideProgress();
                        if (null != movieDetailBean) {
                            mMovieDetailView.onMovieDetailCompleted(movieDetailBean);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void loadComments(MovieBean.Movie movie, int pageIndex) {

        mMovieDetailView.showProgress();
        mMovieDetailModel.loadComments(movie, pageIndex, new IMovieDetailModel.OnLoadCommentListener() {
            @Override
            public void onLoadFailed() {
                mMovieDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieDetailView.hideProgress();
                    }
                });
            }

            @Override
            public void onLoadCompleted(final List<MovieDetailBean.Comment> commentList, final boolean hasNext) {


                mMovieDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieDetailView.hideProgress();
                        mMovieDetailView.onLoadCommentCompleted(commentList, hasNext);
                    }
                });
            }
        });
    }

    @Override
    public int getFirstIndex() {
        return 1;
    }
}
