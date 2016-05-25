package com.aishang5wpj.juhenews.main.movie.moviedetail;

import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.bean.MovieDetailBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/25下午2:55.
 */
public class MovieDetailModelImpl implements IMovieDetailModel {

    private static final int COMMENT_PAGE_SIZE = 5;

    private static final String MOVIE_DETAIL = "http://m.maoyan.com/movie/%s.json";
    private static final String MOVIE_COMMENT = "http://m.maoyan.com/comments.json?movieid=%s&limit=%s&offset=%s";

    private Gson mGson;
    private OkHttpClient mOkHttpClient;

    public MovieDetailModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadMovieDetal(final MovieBean.Movie movie, final OnLoadMovieDetalListener listener) {

        String url = String.format(MOVIE_DETAIL, movie.id);
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
                MovieDetailBean movieDetailBean = mGson.fromJson(result, MovieDetailBean.class);
                if (null != movieDetailBean) {
                    listener.onLoadCompleted(movieDetailBean);
                }
            }
        });
    }

    @Override
    public void loadComments(MovieBean.Movie movie, int pageIndex, final OnLoadCommentListener listener) {

        String url = String.format(MOVIE_COMMENT, movie.id, COMMENT_PAGE_SIZE, pageIndex * COMMENT_PAGE_SIZE);
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
                MovieDetailBean movieDetailBean = mGson.fromJson(result, MovieDetailBean.class);
                if (null != movieDetailBean) {
                    listener.onLoadCompleted(movieDetailBean.data.CommentResponseModel.cmts
                            , movieDetailBean.data.CommentResponseModel.hasNext);
                }
            }
        });
    }
}
