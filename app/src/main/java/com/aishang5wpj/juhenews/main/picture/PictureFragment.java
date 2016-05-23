package com.aishang5wpj.juhenews.main.picture;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.bean.PictureChannelBean;
import com.aishang5wpj.juhenews.main.news.NewsPagerAdpater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class PictureFragment extends BaseFragment implements IPictureChannelView {

    private IPicturePresenter mPicturePresenter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsPagerAdpater mPagerAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    public void onInitViews() {

        mTabLayout = (TabLayout) findViewById(R.id.picture_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.picture_viewpager);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        mPagerAdpater = new NewsPagerAdpater(getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdpater);
        mTabLayout.setupWithViewPager(mViewPager);

        mPicturePresenter = new PicturePresenterImpl(this);
        mPicturePresenter.loadChannel(getActivity());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void runOnUiThread(Runnable runnable) {

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void onChannelLoad(List<PictureChannelBean.Channel> channelList) {

        mPagerAdpater.clear();
        mTabLayout.removeAllTabs();

        List<String> titleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (PictureChannelBean.Channel channel : channelList) {

            titleList.add(channel.name);
            fragmentList.add(PictureListFragment.newFragment(channel));
            mTabLayout.addTab(mTabLayout.newTab().setText(channel.name));
        }
        mPagerAdpater.setData(fragmentList, titleList);
        mViewPager.setOffscreenPageLimit(channelList.size() - 1);
    }
}
