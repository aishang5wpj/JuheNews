package com.aishang5wpj.juhenews.bean;

import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:49.
 */
public class MovieBean {

    public String title;
    public List<Movie> subjects;

    public class Movie {

        /**
         * 电影标签、分类
         */
        public List<String> genres;
        /**
         * 电影名
         */
        public String title;
        /**
         * 原名
         */
        public String original_title;
        /**
         * 又名
         */
        public String aka;
        /**
         * 电影海报
         */
        public Avatars images;
        /**
         * 主演
         */
        public List<Casts> casts;
        /**
         * 导演
         */
        public List<Casts> directors;
        /**
         * 如果条目类型是电影则为上映日期，如果是电视剧则为首Ï日期
         */
        public String pubdates;
        /**
         * 年代
         */
        public String year;
        /***
         * 语言
         */
        public String languages;
        /***
         * 片长
         */
        public String durations;
        /**
         * 制片国家/地区
         */
        public String countries;
        /**
         * 简介
         */
        public String summary;

        public String getImageUrl() {
            return getImageUrl(false);
        }

        public String getImageUrl(boolean isLarge) {
            if (null == images || null == images.medium) {
                return "";
            }
            return isLarge ? images.large : images.medium;
        }
    }

    /**
     * 主演
     */
    public class Casts {
        public String alt;
        public String name;
        public String id;
        public Avatars avatars;
    }

    /**
     * 影人头像，分别提供420px x 600px(大)，140px x 200px(中) 70px x 100px(小)尺寸
     */
    public class Avatars {
        public String small;
        public String medium;
        public String large;
    }
}
