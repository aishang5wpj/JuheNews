package com.aishang5wpj.juhenews.utils;

import android.widget.ImageView;

import com.aishang5wpj.juhenews.R;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by wpj on 16/5/24上午9:31.
 * http://blog.csdn.net/shangmingchao/article/details/51125554
 */
public class ImageUtils {

    private ImageUtils() {

    }

    public static ImageUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void display(ImageView imageView, String url) {

        display(imageView, url, R.mipmap.ic_photo_size_select_actual_white_24dp
                , R.mipmap.ic_photo_size_select_actual_white_24dp);
    }

    public void display(ImageView imageView, String url, int loadingImg, int errorImg) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        DrawableTypeRequest request = Glide.with(imageView.getContext()).load(url);
        if (0 != loadingImg) {
            request.placeholder(loadingImg);
        }
        if (0 != errorImg) {
            request.error(errorImg);
        }
        request
                .thumbnail(0.5f)//缩略图和大图的比例系数，如果缩略图先被加载出来则先显示缩略图
                .fitCenter()
//                .centerCrop()
                .crossFade()
                .dontAnimate()//解决加载出来的瞬间闪一下的问题
                .into(imageView);
    }

    private static final class LazyHolder {
        private static final ImageUtils INSTANCE = new ImageUtils();
    }
}
