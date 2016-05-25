package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/25上午10:57.
 *
 * @see http://www.jianshu.com/p/9855610eb1d4
 */
public class MovieModelImpl implements IMovieModel {

    private static final int PAGE_SIZE = 10;
    private static final String API = "http://m.maoyan.com/movie/list.json?type=hot&offset=%s&limit=%s";
    private Gson mGson;
    private OkHttpClient mOkHttpClient;

    public MovieModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadMovies(int pageIndex, final OnLoadMovieListener listener) {

        String url = String.format(API, pageIndex * PAGE_SIZE, PAGE_SIZE);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (null != listener) {
                    listener.onLoadFailed();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string().replaceAll("\\s", "");
                MovieBean movieBean = mGson.fromJson(result, MovieBean.class);
                if (null != listener) {
                    listener.onLoadCompleted(movieBean);
                }
            }
        });
    }
}
