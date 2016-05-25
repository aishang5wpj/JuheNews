package com.aishang5wpj.juhenews.main.movie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public class MoviePresenterImpl implements IMoviePresenter {

    private IMovieModel mMovieModel;
    private IMovieChannelView mMovieChannelView;
    private IMovieView mMovieView;

    private MoviePresenterImpl() {
        mMovieModel = new MovieModelImpl();
    }

    public MoviePresenterImpl(IMovieChannelView channelView) {
        this();
        mMovieChannelView = channelView;
    }

    public MoviePresenterImpl(IMovieView movieView) {
        this();
        mMovieView = movieView;
    }

    @Override
    public void loadChannel(Context context) {

        mMovieModel.loadChannel(context, new IMovieModel.OnLoadChannelListener() {
            @Override
            public void onLoadComplted(List<MovieChannelBean> channelList) {

                mMovieChannelView.onLoadChannelComplted(channelList);
            }
        });
    }

    @Override
    public void loadMovies(MovieChannelBean channel, int pageIndex) {

        mMovieView.showProgress();
        mMovieModel.loadMovies(channel, pageIndex, new IMovieModel.OnLoadMoviesListener() {
            @Override
            public void onLoadComplted(final MovieBean movieBean) {

                if (null == movieBean) {
                    return;
                }
                mMovieView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieView.hideProgress();
                        mMovieView.onMoviesLoadCompleted(movieBean.subjects);
                    }
                });
            }
        });
    }

    @Override
    public int getStartIndex() {
        return 0;
    }
}
