package com.aishang5wpj.juhenews.main.movie;

import com.aishang5wpj.juhenews.bean.MovieChannelBean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:17.
 */
public interface IMovieChannelView {
    void onLoadChannelComplted(List<MovieChannelBean> channelList);
}
