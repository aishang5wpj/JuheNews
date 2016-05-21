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
 * Created by wpj on 16/5/17上午10:27.
 */
public class NewsBean implements Serializable {

    private static final String NEWS_DETAIL = "http://c.m.163.com/nc/article/%s/full.html";

    public String ltitle;
    public String digest;
    public String docid;
    public String title;
    public String source;
    public String imgsrc;
    public List<ImageExtra> imgextra;

    public static List<NewsBean> parseNewsList(String result) {

        List<NewsBean> newsList = new ArrayList<>(1);
        try {
            JSONObject jsonObject = new JSONObject(result);
            for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {

                String key = iterator.next();
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                int size = jsonArray.length();
                for (int i = 0; i < size; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    NewsBean newsBean = new NewsBean();
                    newsBean.ltitle = JsonUtil.getValue(object, "ltitle");
                    newsBean.digest = JsonUtil.getValue(object, "digest");
                    newsBean.docid = JsonUtil.getValue(object, "docid");
                    newsBean.title = JsonUtil.getValue(object, "title");
                    newsBean.source = JsonUtil.getValue(object, "source");
                    newsBean.imgsrc = JsonUtil.getValue(object, "imgsrc");
                    JSONArray array = JsonUtil.getArray(object, "imgextra");
                    if (null != array) {

                        newsBean.imgextra = new ArrayList<>();

                        int length = array.length();
                        for (int j = 0; j < length; j++) {
                            JSONObject temp = array.getJSONObject(j);
                            ImageExtra extra = new ImageExtra();
                            extra.imgsrc = JsonUtil.getValue(temp, "imgsrc");

                            newsBean.imgextra.add(extra);
                        }
                    }
                    newsList.add(newsBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public int getImgExtraCount() {
        return imgextra == null ? 0 : imgextra.size();
    }

    public String getDetailUrl() {
        return String.format(NEWS_DETAIL, docid);
    }

    public static class ImageExtra implements Serializable {
        public String imgsrc;
    }
}
