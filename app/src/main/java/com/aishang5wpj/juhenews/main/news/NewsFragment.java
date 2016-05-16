package com.aishang5wpj.juhenews.main.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class NewsFragment extends BaseFragment {

    /**
     * http://apistore.baidu.com/apiworks/servicedetail/688.html
     */
    public static final int NEWS_RECOMMEND = 0;
    /**
     * http://apistore.baidu.com/apiworks/servicedetail/632.html
     */
    public static final int NEWS_WEI_XIN = 1;
    /**
     * http://apistore.baidu.com/apiworks/servicedetail/868.html
     */
    public static final int NEWS_SOCIETY = 2;
    /**
     * http://apistore.baidu.com/apiworks/servicedetail/1061.html
     */
    public static final int NEWS_TECHNOLOGY = 3;
    /***
     * http://apistore.baidu.com/apiworks/servicedetail/711.html
     */
    public static final int NEWS_SPORT = 4;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsPagerAdpater mPagerAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onInitViews() {

        mTabLayout = (TabLayout) findViewById(R.id.news_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.news_viewpager);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {
        mPagerAdpater = new NewsPagerAdpater(getChildFragmentManager());
        mPagerAdpater.addFragment(NewsListFragment.newInstance(NEWS_RECOMMEND), getString(R.string.news_recommend));
        mPagerAdpater.addFragment(NewsListFragment.newInstance(NEWS_WEI_XIN), getString(R.string.news_weixin));
        mPagerAdpater.addFragment(NewsListFragment.newInstance(NEWS_SOCIETY), getString(R.string.news_society));
        mPagerAdpater.addFragment(NewsListFragment.newInstance(NEWS_TECHNOLOGY), getString(R.string.news_technology));
        mPagerAdpater.addFragment(NewsListFragment.newInstance(NEWS_SPORT), getString(R.string.news_sport));

        mViewPager.setAdapter(mPagerAdpater);
        mViewPager.setOffscreenPageLimit(4);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_weixin));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_society));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_technology));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_sport));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {

    }
}