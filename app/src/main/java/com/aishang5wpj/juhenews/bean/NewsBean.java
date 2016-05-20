package com.aishang5wpj.juhenews.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wpj on 16/5/17上午10:27.
 */
public class NewsBean {

    private static final String NEWS_DETAIL = "http://c.m.163.com/nc/article/%s/full.html";

    public String ltitle;
    public String digest;
    public String docid;
    public String title;
    public String source;
    public String imgsrc;

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
                    newsBean.ltitle = object.getString("ltitle");
                    newsBean.digest = object.getString("digest");
                    newsBean.docid = object.getString("docid");
                    newsBean.title = object.getString("title");
                    newsBean.source = object.getString("source");
                    newsBean.imgsrc = object.getString("imgsrc");
                    newsList.add(newsBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public String getDetailUrl() {
        return String.format(NEWS_DETAIL, docid);
    }

    public class AD {
        public String title;
        public String tag;
        public String imgsrc;
        public String subtitle;
        public String url;
    }

    public class ImageExtra {
        public String imgextra;
    }
}
