package com.aishang5wpj.juhenews.main.picture;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.PictureBean;
import com.aishang5wpj.juhenews.bean.PictureChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午1:47.
 */
public interface IPictureModel {

    void loadChannel(Context context, OnLoadChannelListener listener);

    void loadPictures(PictureChannelBean.Channel channel, int page, OnLoadPictureListener loadPictureListener);

    interface OnLoadPictureListener {
        void onLoadCompleted(List<PictureBean.Picture> pictureList);
    }

    interface OnLoadChannelListener {
        void onLoadCompleted(List<PictureChannelBean.Channel> channelList);
    }
}
