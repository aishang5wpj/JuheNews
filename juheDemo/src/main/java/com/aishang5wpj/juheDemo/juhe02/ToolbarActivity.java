package com.aishang5wpj.juheDemo.juhe02;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aishang5wpj.juheDemo.R;

public class ToolbarActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //先调用这个，才能设置向上箭头、给菜单重新设置监听器等
        setSupportActionBar(mToolbar);
        //设置向上箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //先设置actionbar，再设置clicklistener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //重新设置一个菜单（似乎并没有什么卵用，为什么呢？）
        mToolbar.inflateMenu(R.menu.menu_set);
        //设置菜单点击事件，会覆盖Activity中的onOptionsItemSelected()方法
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Snackbar.make(mToolbar, item.getTitle(), Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置logo
        mToolbar.setLogo(R.mipmap.ic_launcher);
        //设置主标题
        mToolbar.setTitle("title");
        //设置主标题字体颜色
        mToolbar.setTitleTextColor(Color.WHITE);
        //设置副标题
        mToolbar.setSubtitle("subTitle");
        //设置副标题字体颜色
        mToolbar.setSubtitleTextColor(Color.GREEN);

        mToolbar.setNavigationIcon(R.mipmap.back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Snackbar.make(mToolbar, item.getTitle(), Snackbar.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }
}
