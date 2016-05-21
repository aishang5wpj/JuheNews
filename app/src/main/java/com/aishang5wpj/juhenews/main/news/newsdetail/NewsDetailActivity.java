package com.aishang5wpj.juhenews.main.news.newsdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.bean.NewsBean;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by wpj on 16/5/20下午6:31.
 */
public class NewsDetailActivity extends BaseActivity implements INewsDetailView {

    public static final String NEWS_BEAN = "newsBean";
    private NewsBean mNewsBean;
    private HtmlTextView mHtmlTextView;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private SimpleDraweeView mSimpleDraweeView;
    private INewsDetailPresenter mDetailPresenter;

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
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.news_detail_iv);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mNewsBean = (NewsBean) getIntent().getSerializableExtra(NEWS_BEAN);
        mToolbar.setTitle(mNewsBean.title);
        mSimpleDraweeView.setImageURI(Uri.parse(mNewsBean.imgsrc));

        mDetailPresenter = new NewsDetailPresenterImpl(this);
        mDetailPresenter.loadNewsDetail(mNewsBean);
    }

    @Override
    public void onClick(View v) {

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

        mHtmlTextView.setHtmlFromString(newsDetailBean.body, new HtmlTextView.LocalImageGetter());
    }
}
