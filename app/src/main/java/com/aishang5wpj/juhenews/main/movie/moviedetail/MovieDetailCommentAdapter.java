package com.aishang5wpj.juhenews.main.movie.moviedetail;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.MovieDetailBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午4:41.
 */
public class MovieDetailCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDetailBean.Comment> mCommentList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_detail_comment_item, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CommentHolder commentHolder = (CommentHolder) holder;
        MovieDetailBean.Comment comment = mCommentList.get(position);
        ImageUtils.getInstance().displayCircleImg(commentHolder.iconIv, comment.avatarurl);
        commentHolder.infoTv.setText(coloringStr(comment.nickName, comment.time));
        commentHolder.scoreTv.setText(comment.score);
        commentHolder.contentTv.setText(comment.content);
    }

    private SpannableString coloringStr(String nickName, String time) {

        String str = String.format("%s %s", nickName, time);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")),
                nickName.length(), str.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true),
                nickName.length(), str.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    @Override
    public int getItemCount() {
        return mCommentList == null ? 0 : mCommentList.size();
    }

    public void setData(List<MovieDetailBean.Comment> commentList) {
        if (null == commentList) {
            return;
        }
        mCommentList = commentList;
        notifyDataSetChanged();
    }

    public void addData(List<MovieDetailBean.Comment> commentList) {
        if (null == commentList) {
            return;
        }
        mCommentList.addAll(commentList);
        notifyDataSetChanged();
    }

    private class CommentHolder extends RecyclerView.ViewHolder {

        ImageView iconIv;
        TextView infoTv, scoreTv, contentTv;

        public CommentHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.movie_detail_comment_icon_iv);
            infoTv = (TextView) itemView.findViewById(R.id.movie_detail_comment_info_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.movie_detail_comment_score_tv);
            contentTv = (TextView) itemView.findViewById(R.id.movie_detail_comment_content_tv);
        }
    }
}
