package com.aishang5wpj.juhenews.main.news;

import com.aishang5wpj.juhenews.bean.PictureBean;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午2:38.
 */
public interface IPictureView {

    void showProgress();

    void hideProgress();

    void runOnUiThread(Runnable runnable);

    void onPicturesLoadCompleted(List<PictureBean.Picture> pictureList);
}
