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
public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_PIC_1 = 0;
    private static final int TYPE_PIC_2 = 1;
    private List<NewsBean> mNewsList;
    private OnItemClickListener mOnItemClickListener;

    public NewsListAdapter() {

    }

    @Override
    public int getItemViewType(int position) {

        NewsBean newsBean = mNewsList.get(position);

        return newsBean.getImgExtraCount() < 2 ? TYPE_PIC_1 : TYPE_PIC_2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        // 给ViewHolder设置布局文件
        View view;
        switch (viewType) {
            case TYPE_PIC_1:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_pic_1, parent, false);
                holder = new HolderPic1(view);
                break;
            case TYPE_PIC_2:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_pic_2, parent, false);
                holder = new HolderPic2(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        NewsBean news = mNewsList.get(position);

        if (viewHolder instanceof HolderPic1) {

            HolderPic1 holder = (HolderPic1) viewHolder;
            holder.titleTv.setText(news.title);
            holder.descTv.setText(news.ltitle);
            holder.iconIv.setImageURI(Uri.parse(news.imgsrc));
            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(position, mNewsList.get(position));
                    }
                }
            });
        } else if (viewHolder instanceof HolderPic2) {

            HolderPic2 holder = (HolderPic2) viewHolder;
            holder.titleTv.setText(news.title);
            holder.iconIv1.setImageURI(Uri.parse(news.imgextra.get(0).imgsrc));
            holder.iconIv2.setImageURI(Uri.parse(news.imgextra.get(1).imgsrc));
            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(position, mNewsList.get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public void setData(List<NewsBean> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void addData(List<NewsBean> newsList) {
        if (null != newsList) {
            mNewsList.addAll(newsList);
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, NewsBean newsBean);
    }

    class HolderPic1 extends RecyclerView.ViewHolder {

        SimpleDraweeView iconIv;
        TextView titleTv, descTv;
        ViewGroup rootLayout;

        public HolderPic1(View itemView) {
            super(itemView);
            rootLayout = (ViewGroup) itemView.findViewById(R.id.news_item_layout);
            iconIv = (SimpleDraweeView) itemView.findViewById(R.id.news_item_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.news_item_title_tv);
            descTv = (TextView) itemView.findViewById(R.id.news_item_desc_tv);
        }
    }

    class HolderPic2 extends RecyclerView.ViewHolder {

        SimpleDraweeView iconIv1, iconIv2;
        TextView titleTv;
        ViewGroup rootLayout;

        public HolderPic2(View itemView) {
            super(itemView);
            rootLayout = (ViewGroup) itemView.findViewById(R.id.news_item_layout);
            iconIv1 = (SimpleDraweeView) itemView.findViewById(R.id.news_item_icon_iv_1);
            iconIv2 = (SimpleDraweeView) itemView.findViewById(R.id.news_item_icon_iv_2);
            titleTv = (TextView) itemView.findViewById(R.id.news_item_title_tv);
        }
    }
}
