package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieBean;

/**
 * Created by wpj on 16/5/25上午10:57.
 */
public interface IMovieModel {

    void loadMovies(int pageIndex, OnLoadMovieListener listener);

    interface OnLoadMovieListener {

        void onLoadFailed();

        void onLoadCompleted(MovieBean movieBean);
    }
}
