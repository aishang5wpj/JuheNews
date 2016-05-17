package com.aishang5wpj.juhenews.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/17下午1:16.
 */
public class NewsChannelBean {
    public String showapi_res_code = "";
    public String showapi_res_error = "";
    public Body showapi_res_body;

    public NewsChannelBean() {
        showapi_res_body = new Body();
    }

    public class Body {
        public String ret_code = "";
        public String totalNum = "";
        public List<Channel> channelList;

        public Body() {
            channelList = new ArrayList<>(1);
        }
    }

    public class Channel implements Serializable {
        public String channelId = "";
        public String name = "";
    }
}
