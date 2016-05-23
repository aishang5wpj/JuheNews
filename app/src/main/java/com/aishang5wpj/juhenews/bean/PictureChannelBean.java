package com.aishang5wpj.juhenews.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wpj on 16/5/23下午2:06.
 */
public class PictureChannelBean {

    public boolean status;
    public List<Channel> tngou;

    public class Channel implements Serializable {
        public String description;
        public int id;
        public String keywords;
        public String name;
        public int seq;
        public String title;
        private boolean enable = false;

        public boolean isDisable() {
            return enable;
        }
    }
}
