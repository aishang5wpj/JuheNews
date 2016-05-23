package com.aishang5wpj.juhenews.bean;

import com.aishang5wpj.juhenews.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wpj on 16/5/21下午4:15.
 */
public class NewsDetailBean implements Serializable {
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
    public List<Img> img;

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
                if (JsonUtil.hasValue(object, "img")) {
                    detailBean.img = new ArrayList<>();
                    JSONArray array = JsonUtil.getArray(object, "img");
                    if (null != array) {
                        int size = array.length();
                        for (int i = 0; i < size; i++) {

                            JSONObject temp = array.getJSONObject(i);

                            Img img = new Img();
                            img.alt = JsonUtil.getValue(temp, "alt");
                            img.pixel = JsonUtil.getValue(temp, "pixel");
                            img.ref = JsonUtil.getValue(temp, "ref");
                            img.src = JsonUtil.getValue(temp, "src");
                            detailBean.img.add(img);
                        }
                    }
                }

                return detailBean;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detailBean;
    }

    public boolean hasImageList() {
        return img != null && img.size() > 0;
    }

    public static class Img implements Serializable {
        public String ref;
        public String pixel;
        public String alt;
        public String src;
    }
}
