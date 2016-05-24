package com.aishang5wpj.juhenews.bean;

import java.util.List;

/**
 * Created by wpj on 16/5/24上午11:25.
 */
public class JokeBean {

    public String status;
    public String desc;
    public List<Joke> detail;

    public class Joke {

        public String id;
        public String xhid;
        public String author;
        public String content;
        public String picUrl;
        public String status;
    }
}
