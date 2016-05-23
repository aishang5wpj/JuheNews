package com.aishang5wpj.juhenews.main.news.newsdetail;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/21下午3:30.
 */
public class NewsDetailModelImpl implements INewsDetailModel {

    private OkHttpClient mOkHttpClient;

    public NewsDetailModelImpl() {
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadNewsDetail(NewsBean newsBean, final OnNewsDetailLoadListener loadListener) {

        String url = newsBean.getDetailUrl();
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (null != loadListener) {
                            loadListener.onLoadFailed();
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String result = response.body().string();
                        NewsDetailBean detailBean = NewsDetailBean.parseNewsDetail(result);
                        if (null != loadListener) {
                            loadListener.onLoadComplete(detailBean);
                        }
                    }
                });
    }
}
