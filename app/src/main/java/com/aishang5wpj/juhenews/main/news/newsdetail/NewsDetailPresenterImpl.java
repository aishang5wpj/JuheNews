package com.aishang5wpj.juhenews.main.news.newsdetail;

import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;

/**
 * Created by wpj on 16/5/21下午3:30.
 */
public class NewsDetailPresenterImpl implements INewsDetailPresenter {

    private INewsDetailView mDetailView;
    private INewsDetailModel mDetailModel;

    public NewsDetailPresenterImpl(INewsDetailView detailView) {
        mDetailView = detailView;
        mDetailModel = new NewsDetailModelImpl();
    }

    @Override
    public void loadNewsDetail(NewsBean newsBean) {

        mDetailView.showProgress();
        mDetailModel.loadNewsDetail(newsBean, new INewsDetailModel.OnNewsDetailLoadListener() {

            @Override
            public void onLoadFailed() {
                mDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mDetailView.hideProgress();
                        mDetailView.showMessage("加载失败");
                    }
                });
            }

            @Override
            public void onLoadComplete(final NewsDetailBean detailBean) {

                mDetailView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mDetailView.hideProgress();
                        mDetailView.onNewsDetailLoadListener(detailBean);
                    }
                });
            }
        });
    }
}
