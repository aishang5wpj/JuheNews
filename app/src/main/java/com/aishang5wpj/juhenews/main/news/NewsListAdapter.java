package com.aishang5wpj.juhenews.main.news;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wpj on 16/5/14下午3:37.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder> {

    private List<NewsBean> mNewsList;

    public NewsListAdapter() {

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 给ViewHolder设置布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        NewsBean news = mNewsList.get(position);
        holder.titleTv.setText(news.title);
        holder.descTv.setText(news.ltitle);
        holder.iconIv.setImageURI(Uri.parse(news.imgsrc));
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public void setData(List<NewsBean> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView iconIv;
        TextView titleTv, descTv;

        public MyHolder(View itemView) {
            super(itemView);
            iconIv = (SimpleDraweeView) itemView.findViewById(R.id.news_item_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.news_item_title_tv);
            descTv = (TextView) itemView.findViewById(R.id.news_item_desc_tv);
        }
    }
}
