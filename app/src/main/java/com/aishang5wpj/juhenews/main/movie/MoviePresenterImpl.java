package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieBean;

/**
 * Created by wpj on 16/5/25上午10:56.
 */
public class MoviePresenterImpl implements IMoviePresenter {

    private IMovieView mMovieView;
    private IMovieModel mMovieModel;

    public MoviePresenterImpl(IMovieView movieView) {
        mMovieView = movieView;
        mMovieModel = new MovieModelImpl();
    }

    @Override
    public int getStartIndex() {
        return 0;
    }

    @Override
    public void loadMovies(int pageIndex) {

        mMovieView.showProgress();
        mMovieModel.loadMovies(pageIndex, new IMovieModel.OnLoadMovieListener() {
            @Override
            public void onLoadFailed() {

                mMovieView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMovieView.hideProgress();
                        mMovieView.showToast("加载失败");
                    }
                });
            }

            @Override
            public void onLoadCompleted(final MovieBean movieBean) {

                if (null == movieBean) {
                    return;
                }
                mMovieView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mMovieView.hideProgress();
                        mMovieView.onLoadMoviesCompleted(movieBean);
                    }
                });
            }
        });
    }
}
