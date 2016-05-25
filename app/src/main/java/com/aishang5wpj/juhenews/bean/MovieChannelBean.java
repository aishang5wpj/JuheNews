package com.aishang5wpj.juhenews.bean;

import android.text.TextUtils;

import com.aishang5wpj.baiduloc.LocationHelper;
import com.aishang5wpj.juhenews.app.MyApplication;

import java.io.Serializable;

/**
 * Created by wpj on 16/5/24下午5:14.
 */
public class MovieChannelBean implements Serializable {

    private static final int PAGE_SIZE = 10;
    public String title;
    private String url;
    private boolean refreshable;
    private int paramsCount;

    public String getUrl(int page) {

        String result = url;
        switch (paramsCount) {
            case 0:
                //不支持加载刷新
                result = url;
                break;
            case 1:
                //城市名
                String city = TextUtils.isEmpty(MyApplication.CITY) ? LocationHelper.DEFAULT_CITY : MyApplication.CITY;
                result = String.format(url, city);
                break;
            case 2:
                //分页查询
                // start：偏移量，起始位置，不是页的序号
                // count：加载条数
                result = String.format(url, page * PAGE_SIZE, PAGE_SIZE);
                break;
        }
        return result;
    }

    public boolean isRefreshable() {
        return refreshable;
    }
}