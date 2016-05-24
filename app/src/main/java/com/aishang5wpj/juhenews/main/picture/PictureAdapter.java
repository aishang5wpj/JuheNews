package com.aishang5wpj.juhenews.main.picture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aishang5wpj.juhenews.R;
import com.aishang5wpj.juhenews.bean.PictureBean;
import com.aishang5wpj.juhenews.glide.ImageUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/23下午1:53.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PictureBean.Picture> mPictureList;
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

        final PictureBean.Picture picture = mPictureList.get(position);

        PictureHolder pictureHolder = (PictureHolder) holder;
        pictureHolder.mTitleTv.setText(picture.title);
        ImageUtils.getInstance().display(pictureHolder.mImageView, picture.getUrl());
        pictureHolder.mRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mItemClickListener) {
                    mItemClickListener.onItemClick(picture);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPictureList == null ? 0 : mPictureList.size();
    }

    public void setData(List<PictureBean.Picture> pictureList) {
        if (pictureList == null) {
            return;
        }
        mPictureList = pictureList;
        notifyDataSetChanged();
    }

    public void addData(List<PictureBean.Picture> pictureList) {
        if (pictureList == null) {
            return;
        }
        mPictureList.addAll(pictureList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(PictureBean.Picture picture);
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
