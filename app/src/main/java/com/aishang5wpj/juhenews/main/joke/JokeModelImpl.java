package com.aishang5wpj.juhenews.main.joke;

import com.aishang5wpj.juhenews.bean.JokeBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/24上午11:10.
 */
public class JokeModelImpl implements IJokeModel {

    private static final int PAGE_SIZE = 10;
    /**
     * 请求参数：
     * 方式一：    maxXhid:已有的最大笑话ID；minXhid:已有的最小笑话ID；size:要获取的笑话的条数
     * 方式二：    size:要获取的笑话的条数；page:分页请求的页数，从0开始
     */
    private static final String API = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do?page=%s&size=%s";

    private Gson mGson;
    private OkHttpClient mOkHttpClient;

    public JokeModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public int getStartIndex() {
        return 0;
    }

    @Override
    public void loadJokes(int pageIndex, final OnLoadJokesListener loadJokesListener) {

        String url = String.format(API, pageIndex, PAGE_SIZE);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                JokeBean jokeBean = mGson.fromJson(result, JokeBean.class);
                if (null != loadJokesListener) {
                    loadJokesListener.onLoadCompleted(jokeBean);
                }
            }
        });
    }
}
