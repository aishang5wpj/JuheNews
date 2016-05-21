package com.aishang5wpj.juhenews.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by wpj on 16/5/17下午5:53.
 */
public class NewsChannelBean implements Serializable {

    public String name;
    public String hasRange;
    public String enable;
    private String url;

    /**
     * @param startIndex 起始位置
     * @param offset     加载条数
     * @return
     */
    public String getUrl(int startIndex, int offset) {

        //没有范围，直接请求
        if (TextUtils.equals(hasRange, "0")) {
            return String.format(url, offset);
        }
        return String.format(url, startIndex, offset);
    }

    public boolean isEnable() {
        return !TextUtils.equals(enable, "0");
    }
}
