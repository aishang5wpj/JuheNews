package com.aishang5wpj.juhenews.main.news;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;
import com.aishang5wpj.juhenews.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/17上午10:09.
 */
public class NewsModelImpl implements INewsModel {

    //网易接口
    //http://blog.csdn.net/macwhirr123/article/details/46682161
    private static final int PAGE_SIZE = 20;

    private Gson mGson;
    //创建okHttpClient对象
    private OkHttpClient mOkHttpClient;

    public NewsModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadChannel(Context context, OnLoadChannelListener listener) {

        String result = FileUtils.readAssertsFile(context, "channel.json");
        List<NewsChannelBean> channelBean = new Gson().fromJson(result, new TypeToken<List<NewsChannelBean>>() {
        }.getType());
        //去掉不可用的栏目
        for (Iterator<NewsChannelBean> iterator = channelBean.iterator(); iterator.hasNext(); ) {
            NewsChannelBean channel = iterator.next();
            if (!channel.isEnable()) {
                iterator.remove();
            }
        }
        if (null != listener) {
            listener.onLoadCompleted(channelBean);
        }
    }

    @Override
    public void loadNews(NewsChannelBean channel, int page, final OnLoadNewsListener listener) {

        String url = channel.getUrl(page * PAGE_SIZE, PAGE_SIZE);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String result = response.body().string();
                        List<NewsBean> newsList = NewsBean.parseNewsList(result);
                        if (null != listener) {
                            listener.onLoadCompleted(newsList);
                        }
                    }
                });
    }
}
