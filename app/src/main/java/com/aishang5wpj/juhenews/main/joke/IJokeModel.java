package com.aishang5wpj.juhenews.main.joke;

import com.aishang5wpj.juhenews.bean.JokeBean;

/**
 * Created by wpj on 16/5/24上午11:10.
 */
public interface IJokeModel {
    int getStartIndex();

    void loadJokes(int pageIndex, OnLoadJokesListener loadJokesListener);

    interface OnLoadJokesListener {
        void onLoadCompleted(JokeBean jokeBean);
    }
}
