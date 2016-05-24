package com.aishang5wpj.juhenews.main.picture;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.glide.ImageUtils;

/**
 * Created by wpj on 16/5/23下午5:14.
 */
public class PictureDetailActivity extends BaseActivity {

    public static final String PICTURE_URL = "picture";
    private Toolbar mToolbar;
    private ImageView mImageView;
    private String mPictureUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
    }

    @Override
    public void onInitViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageView = (ImageView) findViewById(R.id.picture_detail_iv);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mPictureUrl = getIntent().getStringExtra(PICTURE_URL);
        ImageUtils.getInstance().display(mImageView, mPictureUrl);
    }

    @Override
    public void onClick(View v) {

    }
}
