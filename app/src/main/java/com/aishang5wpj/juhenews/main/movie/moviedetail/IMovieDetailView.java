package com.aishang5wpj.juhenews.main.movie.moviedetail;

import com.aishang5wpj.juhenews.bean.MovieDetailBean;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午2:54.
 */
public interface IMovieDetailView {

    void showProgress();

    void hideProgress();

    void runOnUiThread(Runnable runnable);

    void onMovieDetailCompleted(MovieDetailBean movieDetailBean);

    void onLoadCommentCompleted(List<MovieDetailBean.Comment> commentList, boolean hasNext);
}
