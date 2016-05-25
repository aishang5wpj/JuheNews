package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public interface IMovieView {

    void showProgress();

    void hideProgress();

    void runOnUiThread(Runnable runnable);

    void onMoviesLoadCompleted(List<MovieBean.Movie> MovieList);
}
