package com.aishang5wpj.juhenews.main.movie;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aishang5wpj.baiduloc.LocationHelper;
import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.app.BaseFragment;
import com.aishang5wpj.juhenews.app.MyApplication;
import com.aishang5wpj.juhenews.bean.MovieChannelBean;
import com.aishang5wpj.juhenews.main.news.NewsPagerAdpater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/16上午10:41.
 */
public class MovieFragment extends BaseFragment implements LocationHelper.OnLocationListener, IMovieChannelView {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private IMoviePresenter mMoviePresenter;
    private NewsPagerAdpater mPagerAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onInitViews() {

        mTabLayout = (TabLayout) findViewById(R.id.movie_tl);
        mViewPager = (ViewPager) findViewById(R.id.movie_vp);
    }

    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {

        mPagerAdpater = new NewsPagerAdpater(getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdpater);

        mTabLayout.setupWithViewPager(mViewPager);
        LocationHelper.getHelper().start(this);

        mMoviePresenter = new MoviePresenterImpl(this);
        mMoviePresenter.loadChannel(getActivity());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLocationFailed() {

        MyApplication.CITY = LocationHelper.DEFAULT_CITY;
    }

    @Override
    public void onLocationSuccessed(String city, double latitude, double lontitude) {
        MyApplication.CITY = city;
    }

    @Override
    public void onLoadChannelComplted(List<MovieChannelBean> channelList) {

        mPagerAdpater.clear();
        mTabLayout.removeAllTabs();

        List<String> titleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (MovieChannelBean channel : channelList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(channel.title));
            titleList.add(channel.title);
            fragmentList.add(MovieListFragment.newFragment(channel));
        }
        mPagerAdpater.setData(fragmentList, titleList);
        mViewPager.setOffscreenPageLimit(channelList.size() - 1);
    }
}
