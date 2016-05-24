package com.aishang5wpj.juhenews.main.news.newsdetail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.bean.NewsDetailBean;
import com.aishang5wpj.juhenews.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/23上午11:19.
 */
public class NewsDetailImagesActivity extends BaseActivity {

    public static final String IMAGE_LIST = "imageList";
    private List<NewsDetailBean.Img> mImgList;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_images);
    }

    @Override
    public void onInitViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.news_detail_images_vp);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle("");
        //先设置actionbar，再设置clicklistener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mImgList = (List<NewsDetailBean.Img>) getIntent().getSerializableExtra(IMAGE_LIST);

        List<ImageView> imageViewList = new ArrayList<>();
        for (NewsDetailBean.Img img : mImgList) {

            ImageView imageView = new ImageView(this);
            ImageUtils.getInstance().display(imageView, img.src);
            imageViewList.add(imageView);
        }

        NewsDetailImagesAdapter adapter = new NewsDetailImagesAdapter(imageViewList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
