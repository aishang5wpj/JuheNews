package com.aishang5wpj.juhenews.main.joke;

/**
 * Created by wpj on 16/5/24上午11:10.
 */
public interface IJokePresenter {
    void loadJokes(int pageIndex);

    int getStartIndex();
}
