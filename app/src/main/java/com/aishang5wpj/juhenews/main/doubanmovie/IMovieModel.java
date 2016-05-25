package com.aishang5wpj.juhenews.main.doubanmovie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.DoubanMovieBean;
import com.aishang5wpj.juhenews.bean.DoubanMovieChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public interface IMovieModel {
    void loadChannel(Context context, OnLoadChannelListener listener);

    void loadMovies(DoubanMovieChannelBean channel, int pageIndex, OnLoadMoviesListener loadMoviesListener);

    interface OnLoadChannelListener {
        void onLoadComplted(List<DoubanMovieChannelBean> channelList);
    }

    interface OnLoadMoviesListener {
        void onLoadFailed();

        void onLoadComplted(DoubanMovieBean movieBean);
    }
}
