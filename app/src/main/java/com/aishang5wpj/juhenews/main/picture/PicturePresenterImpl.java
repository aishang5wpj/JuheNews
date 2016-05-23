package com.aishang5wpj.juhenews.main.picture;

import android.content.Context;

import com.aishang5wpj.juhenews.bean.PictureBean;
import com.aishang5wpj.juhenews.bean.PictureChannelBean;
import com.aishang5wpj.juhenews.main.news.IPictureView;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午1:47.
 */
public class PicturePresenterImpl implements IPicturePresenter {

    //来福岛----一次只能20条
    //http://www.laifudao.com/api.asp
    //百度------鉴于新闻栏目的尿性，暂时放弃
    //http://apistore.baidu.com/apiworks/servicedetail/720.html
    //天狗美图-----支持分类、分页查询
    //http://www.tngou.net/doc/gallery

    private IPictureView mPictureView;
    private IPictureChannelView mPictureChannelView;
    private IPictureModel mPictureModel;

    public PicturePresenterImpl() {

        mPictureModel = new PictureModelImpl();
    }

    public PicturePresenterImpl(IPictureView pictureView) {
        this();
        mPictureView = pictureView;
    }

    public PicturePresenterImpl(IPictureChannelView pictureView) {
        this();
        mPictureChannelView = pictureView;
    }

    @Override
    public void loadPictures(PictureChannelBean.Channel channel, int page) {

        mPictureView.showProgress();
        mPictureModel.loadPictures(channel, page, new IPictureModel.OnLoadPictureListener() {
            @Override
            public void onLoadCompleted(final List<PictureBean.Picture> pictureList) {

                mPictureView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mPictureView.hideProgress();
                        mPictureView.onPicturesLoadCompleted(pictureList);
                    }
                });
            }
        });
    }

    @Override
    public void loadChannel(Context context) {

        mPictureModel.loadChannel(context, new IPictureModel.OnLoadChannelListener() {
            @Override
            public void onLoadCompleted(final List<PictureChannelBean.Channel> channelList) {

                mPictureChannelView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPictureChannelView.onChannelLoad(channelList);
                    }
                });
            }
        });
    }
}
