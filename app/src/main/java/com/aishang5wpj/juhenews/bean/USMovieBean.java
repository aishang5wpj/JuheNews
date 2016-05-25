package com.aishang5wpj.juhenews.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/24下午5:49.
 */
public class USMovieBean {

    public String title;
    public List<USMovie> subjects;

    public List<DoubanMovieBean.Movie> toMovieList() {
        List<DoubanMovieBean.Movie> movieList = new ArrayList<>();
        for (USMovie subject : subjects) {
            movieList.add(subject.subject);
        }
        return movieList;
    }

    /**
     * 北美票房榜json格式跟其他的不一样
     */
    public class USMovie {
        public String box;
        public String rank;
        public DoubanMovieBean.Movie subject;
    }
}
