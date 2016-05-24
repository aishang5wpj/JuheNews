package com.aishang5wpj.juhenews.main.joke;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.JokeBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/24上午11:25.
 */
public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private List<JokeBean.Joke> mJokeList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final JokeBean.Joke joke = mJokeList.get(position);
        JokeHolder jokeHolder = (JokeHolder) holder;
        jokeHolder.authorTv.setText(joke.author);
        jokeHolder.contentTv.setText(joke.content);
        if (TextUtils.isEmpty(joke.picUrl)) {

            jokeHolder.pictureIv.setVisibility(View.GONE);
        } else {

            jokeHolder.pictureIv.setVisibility(View.VISIBLE);
            ImageUtils.getInstance().display(jokeHolder.pictureIv, joke.picUrl);
        }
        jokeHolder.pictureIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(joke);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJokeList == null ? 0 : mJokeList.size();
    }

    public void setData(List<JokeBean.Joke> jokeList) {
        if (null == jokeList) {
            return;
        }
        mJokeList = jokeList;
        notifyDataSetChanged();
    }

    public void addData(List<JokeBean.Joke> jokeList) {
        if (null == jokeList) {
            return;
        }
        mJokeList.addAll(jokeList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(JokeBean.Joke joke);
    }

    private class JokeHolder extends RecyclerView.ViewHolder {

        ImageView pictureIv;
        TextView authorTv, contentTv;

        public JokeHolder(View itemView) {
            super(itemView);
            authorTv = (TextView) itemView.findViewById(R.id.joke_author_tv);
            contentTv = (TextView) itemView.findViewById(R.id.joke_content_tv);
            pictureIv = (ImageView) itemView.findViewById(R.id.joke_pic_iv);
        }
    }
}
