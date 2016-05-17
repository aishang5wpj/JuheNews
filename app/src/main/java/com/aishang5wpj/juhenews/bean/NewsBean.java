package com.aishang5wpj.juhenews.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/17上午10:27.
 */
public class NewsBean {

    public String showapi_res_code = "";
    public String showapi_res_error = "";
    public Body showapi_res_body;

    public NewsBean() {
        showapi_res_body = new Body();
    }

    public class Body {
        public String ret_code = "";
        public Page pagebean;

        public Body() {
            pagebean = new Page();
        }
    }

    public class Page {
        public String allNum = "";
        public String allPages = "";
        public String currentPage = "";
        public String maxResult = "";
        public List<News> contentlist;

        public Page() {
            contentlist = new ArrayList<>(1);
        }
    }

    public class News {
        public String channelId = "";
        public String channelName = "";
        public String content = "";
        public String desc = "";
        public String html = "";
        public List<Image> imageurls;
        public String link = "";
        public String nid = "";
        public String pubDate = "";//发表时间
        public String sentiment_display = "";
        public String source = "";//新闻来源
        public String title = "";//

        public News() {
            imageurls = new ArrayList<>(1);
        }

        public String getImageUrl() {
            if (null == imageurls || imageurls.size() <= 0) {
                return "";
            }
            return imageurls.get(0).url;
        }
    }

    public class Image {
        public String height = "";
        public String url = "";
        public String width = "";
    }
}
