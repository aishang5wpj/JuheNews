package com.aishang5wpj.juhenews.bean;

import com.aishang5wpj.juhenews.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wpj on 16/5/21下午4:15.
 */
public class NewsDetailBean {
    /**
     * docid
     */
    public String docid;
    /**
     * title
     */
    public String title;
    /**
     * source
     */
    public String source;
    /**
     * body
     */
    public String body;
    /**
     * ptime
     */
    public String ptime;
    /**
     * cover
     */
    public String cover;
    /**
     * 图片列表
     */
    public List<String> imgList;

    public static NewsDetailBean parseNewsDetail(String content) {

        NewsDetailBean detailBean = new NewsDetailBean();
        try {
            JSONObject jsonObject = new JSONObject(content);
            for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {

                String key = iterator.next();

                JSONObject object = JsonUtil.getObject(jsonObject, key);
                detailBean.docid = JsonUtil.getValue(object, "docid");
                detailBean.title = JsonUtil.getValue(object, "title");
                detailBean.source = JsonUtil.getValue(object, "source");
                detailBean.body = JsonUtil.getValue(object, "body");
                detailBean.ptime = JsonUtil.getValue(object, "ptime");
                detailBean.cover = JsonUtil.getValue(object, "cover");

                return detailBean;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detailBean;
    }
}
