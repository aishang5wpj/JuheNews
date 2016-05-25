package com.aishang5wpj.juhenews.main.movie.moviedetail;

import com.aishang5wpj.juhenews.bean.MovieBean;

/**
 * Created by wpj on 16/5/25下午2:53.
 */
public interface IMovieDetailPresenter {
    void loadMovieDetail(MovieBean.Movie movie);

    void loadComments(MovieBean.Movie movie, int pageIndex);

    int getFirstIndex();
}
