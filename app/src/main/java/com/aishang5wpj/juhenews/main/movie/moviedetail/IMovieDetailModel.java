package com.aishang5wpj.juhenews.main.movie.moviedetail;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieDetailBean;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午2:54.
 */
public interface IMovieDetailModel {
    void loadMovieDetal(MovieBean.Movie movie, OnLoadMovieDetalListener listener);

    void loadComments(MovieBean.Movie movie, int pageIndex, OnLoadCommentListener listener);

    interface OnLoadCommentListener {
        void onLoadFailed();

        void onLoadCompleted(List<MovieDetailBean.Comment> commentList, boolean hasNext);
    }

    interface OnLoadMovieDetalListener {
        void onLoadFailed();

        void onLoadCompleted(MovieDetailBean movieDetailBean);
    }
}
