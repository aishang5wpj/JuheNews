package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;
import com.aishang5wpj.juhenews.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/17上午10:09.
 * GET请求，apikey保存在Header
 */
public class NewsModelImpl implements INewsModel {

    /**
     * http://apistore.baidu.com/apiworks/servicedetail/688.html
     * 新闻列表
     */
    private static final String REQUEST_NEWS = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news?";
    /**
     * 新闻栏目
     */
    private static final String REQUEST_CHANNEL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
    private static final String API_KEY = "40ffbe65699a9fea09022c2cdbcc7f8d";
    private Gson mGson;
    //创建okHttpClient对象
    private OkHttpClient mOkHttpClient;

    public NewsModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadChannel(OnLoadChannelListener listener) {
        //好多都是302，是因为频繁请求被百度拒了？还是接口本身不可用了。
        // requestChannel(listener);

    }

    @Override
    public void loadNews(NewsChannelBean.Channel channel, int page, OnLoadNewsListener listener) {

        requestNews(channel.channelId, channel.name, 1, listener);
    }

    private void requestChannel(final OnLoadChannelListener listener) {
        //创建一个Request
        final Request request = new Request.Builder()
                .addHeader("apikey", API_KEY)
                .url(REQUEST_CHANNEL)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                NewsChannelBean newsChannelBean = mGson.fromJson(result, NewsChannelBean.class);
                if (null != listener) {
                    listener.onLoadCompleted(newsChannelBean);
                }
            }
        });
    }

    private void requestNews(String channelId, String channelName, int page, final OnLoadNewsListener listener) {

        StringBuilder stringBuilder = new StringBuilder(REQUEST_NEWS);
        stringBuilder.append("channelId=" + Utils.urlEncoderStr(channelId));
        stringBuilder.append("&channelName=" + Utils.urlEncoderStr(channelName));
        stringBuilder.append("&needHtml=" + 1);
        stringBuilder.append("&page=" + page);
        String url = stringBuilder.toString();
        //创建一个Request
        final Request request = new Request.Builder()
                .addHeader("apikey", API_KEY)
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                NewsBean newsBean = mGson.fromJson(result, NewsBean.class);
                if (null != listener) {
                    listener.onLoadCompleted(newsBean);
                }
            }
        });
    }
}
