package com.aishang5wpj.juhenews.main.doubanmovie;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.DoubanMovieBean;
import com.aishang5wpj.juhenews.bean.DoubanMovieChannelBean;
import com.aishang5wpj.juhenews.bean.USMovieBean;
import com.aishang5wpj.juhenews.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public class MovieModelImpl implements IMovieModel {

    private Gson mGson;
    private OkHttpClient mOkHttpClient;

    public MovieModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadChannel(Context context, OnLoadChannelListener listener) {
        String result = FileUtils.readAssertsFile(context, "movieChannel.json");
        List<DoubanMovieChannelBean> channelList = mGson.fromJson(result, new TypeToken<List<DoubanMovieChannelBean>>
                () {
        }.getType());
        if (null != listener) {
            listener.onLoadComplted(channelList);
        }
    }

    @Override
    public void loadMovies(DoubanMovieChannelBean channel, int pageIndex, final OnLoadMoviesListener
            loadMoviesListener) {

        String url = channel.getUrl(pageIndex);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if (null != loadMoviesListener) {
                    loadMoviesListener.onLoadFailed();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //豆瓣电影api返回的数据里莫名其妙多很多回车换行，将导致Gson解析失败
                String result = response.body().string().replaceAll("\\s", "");
                DoubanMovieBean movieBean = mGson.fromJson(result, DoubanMovieBean.class);
                if (movieBean.subjects != null && movieBean.subjects.size() > 0 && movieBean.subjects.get(0).isEmpty
                        ()) {

                    //对北美票房数据特殊处理
                    USMovieBean usMovieBean = mGson.fromJson(result, USMovieBean.class);
                    movieBean.subjects = usMovieBean.toMovieList();
                }
                if (null != loadMoviesListener) {
                    loadMoviesListener.onLoadComplted(movieBean);
                }
            }
        });
    }
}
