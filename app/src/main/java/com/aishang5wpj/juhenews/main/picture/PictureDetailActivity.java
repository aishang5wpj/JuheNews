package com.aishang5wpj.juhenews.main.picture;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.bean.PictureBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by wpj on 16/5/23下午5:14.
 */
public class PictureDetailActivity extends BaseActivity {

    public static final String PICTURE = "picture";
    private Toolbar mToolbar;
    private SimpleDraweeView mSimpleDraweeView;
    private PictureBean.Picture mPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
    }

    @Override
    public void onInitViews() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.picture_detail_iv);
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
        mPicture = (PictureBean.Picture) getIntent().getSerializableExtra(PICTURE);
        mSimpleDraweeView.setImageURI(Uri.parse(mPicture.getUrl()));
        mSimpleDraweeView.setAspectRatio(1f);
    }

    @Override
    public void onClick(View v) {

    }
}
