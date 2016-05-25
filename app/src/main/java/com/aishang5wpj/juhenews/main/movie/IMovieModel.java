package com.aishang5wpj.juhenews.main.movie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public interface IMovieModel {
    void loadChannel(Context context, OnLoadChannelListener listener);

    void loadMovies(MovieChannelBean channel, int pageIndex, OnLoadMoviesListener loadMoviesListener);

    interface OnLoadChannelListener {
        void onLoadComplted(List<MovieChannelBean> channelList);
    }

    interface OnLoadMoviesListener {
        void onLoadComplted(MovieBean movieBean);
    }
}
