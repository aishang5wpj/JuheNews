package com.aishang5wpj.juhenews.main.doubanmovie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.DoubanMovieBean;
import com.aishang5wpj.juhenews.bean.DoubanMovieChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public class MoviePresenterImpl implements IMoviePresenter {

    private IMovieModel mMovieModel;
    private IMovieChannelView mMovieChannelView;
    private IMovieView mMovieView;
    private boolean mIsLoading = false;

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
            public void onLoadComplted(List<DoubanMovieChannelBean> channelList) {

                mMovieChannelView.onLoadChannelComplted(channelList);
            }
        });
    }

    @Override
    public void loadMovies(DoubanMovieChannelBean channel, int pageIndex) {

        if (mIsLoading) {
            return;
        }
        mIsLoading = true;
        mMovieView.showProgress();
        mMovieModel.loadMovies(channel, pageIndex, new IMovieModel.OnLoadMoviesListener() {
            @Override
            public void onLoadFailed() {
                mIsLoading = false;
                mMovieView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieView.hideProgress();
                    }
                });
            }

            @Override
            public void onLoadComplted(final DoubanMovieBean movieBean) {

                mIsLoading = false;
                mMovieView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieView.hideProgress();
                        if (null != movieBean) {

                            mMovieView.onMoviesLoadCompleted(movieBean.subjects);
                        }
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
