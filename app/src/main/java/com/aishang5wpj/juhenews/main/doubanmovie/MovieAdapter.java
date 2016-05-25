package com.aishang5wpj.juhenews.main.doubanmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.DoubanMovieBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午1:53.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DoubanMovieBean.Movie> mMovieList;
    private OnItemClickListener mItemClickListener;

    private float dp2px(Context context, int value) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //不要直接传null，要传parent, false，否则imageview初始的宽高始终是0
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);

        return new PictureHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final DoubanMovieBean.Movie movie = mMovieList.get(position);

        PictureHolder pictureHolder = (PictureHolder) holder;
        pictureHolder.mTitleTv.setText(movie.title);
        ImageUtils.getInstance().display(pictureHolder.mImageView, movie.getImageUrl());
        pictureHolder.mRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mItemClickListener) {
                    mItemClickListener.onItemClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void setData(List<DoubanMovieBean.Movie> movieList) {
        if (movieList == null) {
            return;
        }
        mMovieList = movieList;
        notifyDataSetChanged();
    }

    public void addData(List<DoubanMovieBean.Movie> movieList) {
        if (movieList == null) {
            return;
        }
        mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DoubanMovieBean.Movie movie);
    }

    private class PictureHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv;
        ViewGroup mRootLayout;
        ImageView mImageView;

        public PictureHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.picture_item_title_tv);
            mRootLayout = (ViewGroup) itemView.findViewById(R.id.picture_item_layout);
            mImageView = (ImageView) itemView.findViewById(R.id.picture_item_icon_iv);
        }
    }
}
