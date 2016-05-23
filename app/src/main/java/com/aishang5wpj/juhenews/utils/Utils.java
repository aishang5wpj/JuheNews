package com.aishang5wpj.juhenews.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wpj on 16/5/17下午2:38.
 */
public class Utils {

    public static String urlEncoderStr(String string) {
        try {
            return URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static Drawable getDrawable(Context context, int resId) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            return context.getDrawable(resId);
        }
        return context.getResources().getDrawable(resId);
    }
}
