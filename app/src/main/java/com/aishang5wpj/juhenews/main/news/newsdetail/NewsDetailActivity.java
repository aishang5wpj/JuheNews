package com.aishang5wpj.juhenews.main.news.newsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.Serializable;

/**
 * Created by wpj on 16/5/20下午6:31.
 */
public class NewsDetailActivity extends BaseActivity implements INewsDetailView {

    public static final String NEWS_BEAN = "newsBean";
    private NewsBean mNewsBean;
    private HtmlTextView mHtmlTextView;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private INewsDetailPresenter mDetailPresenter;
    private NewsDetailBean mNewsDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
    }

    @Override
    public void onInitViews() {
        mHtmlTextView = (HtmlTextView) findViewById(R.id.news_detail_tv);
        mToolbar = (Toolbar) findViewById(R.id.news_detail_tb);
        mProgressBar = (ProgressBar) findViewById(R.id.news_detail_pb);
        mImageView = (ImageView) findViewById(R.id.news_detail_iv);
    }

    @Override
    public void onInitListeners() {

        mImageView.setOnClickListener(this);
    }

    @Override
    public void onInitData() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //先设置actionbar，再设置clicklistener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mNewsBean = (NewsBean) getIntent().getSerializableExtra(NEWS_BEAN);
        mToolbar.setTitle(mNewsBean.title);

        //占位图太大了，暂时隐藏
        ImageUtils.getInstance().display(mImageView, mNewsBean.imgsrc, 0, 0);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.news_detail_ctl);
        collapsingToolbar.setTitle(mNewsBean.title);

        mDetailPresenter = new NewsDetailPresenterImpl(this);
        mDetailPresenter.loadNewsDetail(mNewsBean);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.news_detail_iv:

                if (null != mNewsDetailBean && mNewsDetailBean.hasImageList()) {
                    Intent intent = new Intent(this, NewsDetailImagesActivity.class);
                    intent.putExtra(NewsDetailImagesActivity.IMAGE_LIST, (Serializable) mNewsDetailBean.img);
                    startActivity(intent);
                }

                break;
        }
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onNewsDetailLoadListener(NewsDetailBean newsDetailBean) {

        mNewsDetailBean = newsDetailBean;
        mHtmlTextView.setHtmlFromString(newsDetailBean.body, new HtmlTextView.LocalImageGetter());
    }
}
