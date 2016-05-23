package com.aishang5wpj.juhenews.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wpj on 16/5/23下午2:49.
 * <p/>
 * total返回的是数据总数，而并非返回的条数（返回条数有参数rows决定）
 * <p/>
 * img字段返回的是不完整的图片路径src，
 * 需要在前面添加【http://tnfs.tngou.net/image】或者【http://tnfs.tngou.net/img】
 * 前者可以再图片后面添加宽度和高度，如：http://tnfs.tngou.net/image/top/default.jpg_180x120
 *
 * @see http://www.tngou.net/doc/gallery/31
 */
public class PictureBean {

    public boolean status;
    public int total;
    public List<Picture> tngou;

    public class Picture implements Serializable {

        public int count;
        public int fcount;
        public int galleryclass;
        public int id;
        public int rcount;
        public int size;
        public long time;
        public String title;
        private String img;

        public String getUrl() {

            return "http://tnfs.tngou.net/image" + img;
        }
    }
}
