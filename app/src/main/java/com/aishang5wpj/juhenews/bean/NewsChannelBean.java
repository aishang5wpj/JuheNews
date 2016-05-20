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
     * @param pageNum  从0开始
     * @param pageSize
     * @return
     */
    public String getUrl(int pageNum, int pageSize) {

        //没有范围，直接请求
        if (TextUtils.equals(hasRange, "0")) {
            return String.format(url, pageSize);
        }
        //0-20
        return String.format(url, (pageNum + 1) * pageSize, pageNum * pageSize);
    }

    public boolean isEnable() {
        return !TextUtils.equals(enable, "0");
    }
}
