package com.aishang5wpj.juhenews.main.doubanmovie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.DoubanMovieChannelBean;

/**
 * Created by wpj on 16/5/24下午5:16.
 */
public interface IMoviePresenter {
    void loadChannel(Context context);

    void loadMovies(DoubanMovieChannelBean channel, int pageIndex);

    int getStartIndex();
}
