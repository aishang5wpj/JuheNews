package com.aishang5wpj.juhenews.main.joke;

import com.aishang5wpj.juhenews.bean.JokeBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24上午11:10.
 */
public interface IJokeView {
    void runOnUiThread(Runnable runnable);

    void showProgress();

    void hideProgress();

    void onLoadCompleted(List<JokeBean.Joke> detail);
}
