package com.aishang5wpj.juhenews.main.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.NewsChannelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class NewsFragment extends BaseFragment implements INewsChannelView {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsPagerAdpater mPagerAdpater;
    private NewsPresenterImpl mNewsPresenter;

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
        mViewPager.setAdapter(mPagerAdpater);
        mTabLayout.setupWithViewPager(mViewPager);

        mNewsPresenter = new NewsPresenterImpl(this);
        mNewsPresenter.loadChannel(getActivity());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoadChannelCompleted(List<NewsChannelBean> channelList) {

        mPagerAdpater.clear();
        mTabLayout.removeAllTabs();

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (NewsChannelBean channel : channelList) {

            fragmentList.add(NewsListFragment.newInstance(channel));
            nameList.add(channel.name);
            mTabLayout.addTab(mTabLayout.newTab().setText(channel.name));
        }
        mPagerAdpater.setData(fragmentList, nameList);
        mViewPager.setOffscreenPageLimit(channelList.size() - 1);
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }
}