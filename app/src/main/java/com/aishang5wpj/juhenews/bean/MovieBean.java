package com.aishang5wpj.juhenews.bean;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wpj on 16/5/25上午11:11.
 */
public class MovieBean {

    public String status;
    public Data data;

    public class Data {

        public boolean hasNext;
        public List<Movie> movies;
    }

    public class Movie {
        public String cnms;//0
        public String sn;//0
        public String late;//false
        public String showInfo;//今天174家影院放映2159场
        public String src;//
        public String showDate;//
        public String pn;//128
        public String preSale;//0
        public String vd;//
        public String dir;//费格尔·雷利,克莱·凯蒂
        public String star;//杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德
        public String cat;//动画,奇幻,冒险
        public String wish;//123045（想看）
        public String nm;//愤怒的小鸟
        public String sc;//8.9
        public String ver;//3D/中国巨幕
        public String rt;//2016-05-20上映
        public String dur;//97
        public String img;//http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg
        public String snum;//83528（评论？）
        public String scm;//绿猪来偷蛋，鸟儿群奋战
        public String imax;//false
        public String id;//246188
        public String time;//

        public String getImageUrl() {

            Pattern pattern = Pattern.compile("p0.meituan.net/(w|[0-9]+)[.](h|[0-9]+)/movie/");
            Matcher matcher = pattern.matcher(img);
            return matcher.replaceAll("p0.meituan.net/720.1080/movie/");
        }

        public String getImageUrl(int w, int h) {
            return img;
        }
    }
}
