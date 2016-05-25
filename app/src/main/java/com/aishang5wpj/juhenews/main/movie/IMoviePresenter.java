package com.aishang5wpj.juhenews.main.movie;

/**
 * Created by wpj on 16/5/25上午10:56.
 */
public interface IMoviePresenter {
    int getStartIndex();

    void loadMovies(int pageIndex);
}
