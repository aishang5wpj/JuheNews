package com.aishang5wpj.juhenews.main.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;

/**
 * Created by wpj on 16/5/14下午3:37.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder> {

    private String[] mItems = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
            "r", "s", "t", "u",};

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

        holder.menuName.setText(mItems[position]);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView menuName;

        public MyHolder(View itemView) {
            super(itemView);
            menuName = (TextView) itemView.findViewById(R.id.news_title_tv);
        }
    }
}
