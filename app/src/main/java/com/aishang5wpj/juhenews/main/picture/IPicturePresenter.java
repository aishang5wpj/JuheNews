package com.aishang5wpj.juhenews.main.picture;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.PictureChannelBean;

/**
 * Created by wpj on 16/5/23下午1:46.
 */
public interface IPicturePresenter {
    void loadPictures(PictureChannelBean.Channel channel, int page);

    void loadChannel(Context context);
}
