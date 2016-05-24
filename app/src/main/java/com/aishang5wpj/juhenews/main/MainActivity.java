package com.aishang5wpj.juhenews.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseActivity;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.glide.ImageUtils;
import com.aishang5wpj.juhenews.main.about.AboutFragment;
import com.aishang5wpj.juhenews.main.joke.JokeFragment;
import com.aishang5wpj.juhenews.main.menu.MenuFragment;
import com.aishang5wpj.juhenews.main.movie.MovieFragment;
import com.aishang5wpj.juhenews.main.news.NewsFragment;
import com.aishang5wpj.juhenews.main.picture.PictureFragment;
import com.aishang5wpj.juhenews.main.weather.WeatherFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Map<String, BaseFragment> mFragmentMap;
    private String mLastFragmentName;
    private ImageView mLogoIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInitViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.main_menu_navigation);

        LinearLayout headerLayout = (LinearLayout) mNavigationView.getHeaderView(0);
        mLogoIv = (ImageView) headerLayout.getChildAt(0);
    }

    @Override
    public void onInitListeners() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(mNavigationView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                switch2Fragment(menuItem.getTitle());
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onInitData() {
        disableSwipeback();
        setSupportActionBar(mToolbar);

        ImageUtils.getInstance().displayCircleImg(mLogoIv, R.mipmap.ftm);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        ///该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
        //mDrawerToggle与mDrawerLayout的打开状态同步
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mFragmentMap = new HashMap<>();
        mFragmentMap.put(getResources().getString(R.string.navigation_news), new NewsFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_movies), new MovieFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_picture), new PictureFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_joke), new JokeFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_menu), new MenuFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_weather), new WeatherFragment());
        mFragmentMap.put(getResources().getString(R.string.navigation_about), new AboutFragment());

        switch2Fragment(getResources().getString(R.string.navigation_picture));
    }

    private void switch2Fragment(CharSequence title) {

        BaseFragment showFragment = mFragmentMap.get(title);
        if (null == showFragment) {
            return;
        }
        //apply title
        mToolbar.setTitle(title);

        String showTag = showFragment.toString();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //hide previous fragment
        Fragment hideFragment = getSupportFragmentManager().findFragmentByTag(mLastFragmentName);
        if (null != hideFragment) {
            ft.hide(hideFragment);
        }
        //show or add target fragment
        if (null == getSupportFragmentManager().findFragmentByTag(showTag)) {
            ft.replace(R.id.frame_content, showFragment, showTag);
        } else {
            ft.show(showFragment);
        }
        //commit
        ft.commit();
        mLastFragmentName = showTag;
    }

    @Override
    public void onClick(View v) {

    }
}