package com.aishang5wpj.juhenews.main.movie;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.MovieBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/25上午11:17.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private List<MovieBean.Movie> mMovieList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MovieBean.Movie movie = mMovieList.get(position);
        MovieHolder movieHolder = (MovieHolder) holder;
        movieHolder.titleTv.setText(coloringStr(movie.nm, movie.rt));
        ImageUtils.getInstance().display(movieHolder.logoIv, movie.getImageUrl());
        movieHolder.logoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(movie);
                }
            }
        });
    }

    private SpannableString coloringStr(String nm, String rt) {

        String str = String.format("%s(%s)", nm, rt);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, nm.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, nm.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private float dp2px(Context context, int value) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void setData(List<MovieBean.Movie> movies) {

        if (null == movies) {
            return;
        }
        mMovieList = movies;
        notifyDataSetChanged();
    }

    public void addData(List<MovieBean.Movie> movies) {

        if (null == movies) {
            return;
        }
        mMovieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MovieBean.Movie movie);
    }

    private class MovieHolder extends RecyclerView.ViewHolder {

        private TextView titleTv;
        private ImageView logoIv;

        public MovieHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.movie_item_title_tv);
            logoIv = (ImageView) itemView.findViewById(R.id.movie_item_logo_iv);
        }
    }
}
