package com.aishang5wpj.juhenews.main.joke;

import com.aishang5wpj.juhenews.bean.JokeBean;

/**
 * Created by wpj on 16/5/24上午11:11.
 * <p/>
 * http://ajita.iteye.com/blog/2188914
 * 示例：http://api.1-blog.com/biz/bizserver/xiaohua/list.do?page=0&size=10
 */
public class JokePresenterImpl implements IJokePresenter {

    private IJokeView mJokeView;
    private IJokeModel mJokeModel;

    public JokePresenterImpl(IJokeView jokeView) {
        mJokeView = jokeView;
        mJokeModel = new JokeModelImpl();
    }

    @Override
    public void loadJokes(int pageIndex) {

        mJokeView.showProgress();
        mJokeModel.loadJokes(pageIndex, new IJokeModel.OnLoadJokesListener() {
            @Override
            public void onLoadCompleted(final JokeBean jokeBean) {

                if (null == jokeBean) {
                    return;
                }
                mJokeView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mJokeView.hideProgress();
                        mJokeView.onLoadCompleted(jokeBean.detail);
                    }
                });
            }
        });
    }

    @Override
    public int getStartIndex() {
        return mJokeModel.getStartIndex();
    }
}
