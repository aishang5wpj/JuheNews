package com.aishang5wpj.juhenews.main.picture;

import com.aishang5wpj.juhenews.bean.PictureChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午1:48.
 */
public interface IPictureChannelView {

    void runOnUiThread(Runnable runnable);

    void onChannelLoad(List<PictureChannelBean.Channel> channelList);
}
