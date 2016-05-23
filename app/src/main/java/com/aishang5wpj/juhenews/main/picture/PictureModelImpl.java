package com.aishang5wpj.juhenews.main.picture;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.PictureBean;
import com.aishang5wpj.juhenews.bean.PictureChannelBean;
import com.aishang5wpj.juhenews.utils.FileUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wpj on 16/5/23下午1:47.
 */
public class PictureModelImpl implements IPictureModel {
    /**
     * @see http://www.tngou.net/doc/gallery/31
     */
    private static final String PICTURE_API = "http://www.tngou.net/tnfs/api/list";

    private static final int PAGE_SIZE = 10;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    public PictureModelImpl() {
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void loadChannel(Context context, OnLoadChannelListener listener) {
        String result = FileUtils.readAssertsFile(context, "pictureChannel.json");

        PictureChannelBean channelBean = new Gson().fromJson(result, PictureChannelBean.class);
        List<PictureChannelBean.Channel> channelList = channelBean.tngou;
        //去掉不可用的栏目
        for (Iterator<PictureChannelBean.Channel> iterator = channelList.iterator(); iterator.hasNext(); ) {
            PictureChannelBean.Channel channel = iterator.next();
            if (channel.isDisable()) {
                iterator.remove();
            }
        }
        if (null != listener) {
            listener.onLoadCompleted(channelBean.tngou);
        }
    }

    @Override
    public void loadPictures(PictureChannelBean.Channel channel, int page, final OnLoadPictureListener
            loadPictureListener) {

        String url = String.format("%s?id=%s&page=%s&rows=%s", PICTURE_API, channel.id, page, PAGE_SIZE);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String result = response.body().string();
                        PictureBean pictureBean = mGson.fromJson(result, PictureBean.class);
                        if (null != loadPictureListener) {
                            loadPictureListener.onLoadCompleted(pictureBean.tngou);
                        }
                    }
                });
    }
}
