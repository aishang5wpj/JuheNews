package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieBean;

/**
 * Created by wpj on 16/5/25上午10:56.
 */
public interface IMovieView {

    void showProgress();

    void hideProgress();

    void runOnUiThread(Runnable runnable);

    void onLoadMoviesCompleted(MovieBean movieBean);

    void showToast(String msg);
}
