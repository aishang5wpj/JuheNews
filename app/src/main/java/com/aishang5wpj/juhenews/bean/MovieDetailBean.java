package com.aishang5wpj.juhenews.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by wpj on 16/5/25下午3:03.
 */
public class MovieDetailBean {

    public String status;
    public Data data;

    public static class MovieDetailModel {
        public String cat;//动画,奇幻,冒险
        public String dealsum;//0
        public String dir;//费格尔·雷利 克莱·凯蒂
        public String dra;//在一座与世隔绝的美丽小岛上，住着一群乐天知命的鸟。不过易怒的大红（杰森·苏戴奇斯 配音）、亢奋的恰克（乔什·盖德 配音）和炸弹（丹尼·麦克布耐德
        // 配音）总是因为各自的怪性格而不被其他的鸟接纳。不过当神秘的绿猪伦纳德（比尔·哈德尔 配音）造访这座世外小岛，就得由这群怪咖查出这些神秘兮兮的外来客到底有何目的......
        public String dur;//97
        public String id;//246188
        public String imax;//false
        public String img;//http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg
        public boolean isShowing;//true
        public String late;//false
        public String mk;//0
        public String nm;//愤怒的小鸟
        public List<String> photos;//
        public String pn;//128
        public String preSale;//0
        public String rt;//2016-05-20上映
        public String sc;//8.9
        public String scm;//绿猪来偷蛋，鸟儿群奋战
        public String showSnum;//true
        public String snum;//83528
        public String src;//美国,芬兰
        public String star;//杰森·苏戴奇斯 乔什·盖德 丹尼·麦克布耐德 玛娅·鲁道夫
        public String vd;//http://v.meituan.net/movie/videos/6720c1c15b0040f0b45855d962e5cacd.mp4
        public String ver;//3D/中国巨幕
        public String vnum;//57
        public String wish;//123045
        public String wishst;//0

        public String getScore() {
            return TextUtils.isEmpty(sc) || TextUtils.equals(sc, "0") ? "暂无" : sc;
        }
    }

    public class Data {
        public MovieDetailModel MovieDetailModel;
        public CommentResponseModel CommentResponseModel;
    }

    public class CommentResponseModel {

        public String total;//0
        public boolean hasNext;

        /**
         * 长评
         */
        public List<Comment> hcmts;

        /**
         * 短评
         */
        public List<Comment> cmts;
    }

    public class Comment {
        public String userId;//144583245
        public String nickName;//封尘
        public String vipInfo;//
        public String score;//4
        public String approve;//37
        public String oppose;//0
        public String reply;//4
        public String avatarurl;//http://p0.meituan.net/avatar/0c6f0f56c20188a35d713ef48a162b92139580.jpg
        public String nick;//封尘落殇
        public String id;//67146629
        //没想到自己居然会看这么“童趣”的电影😂并不是这款游戏的粉，刚开始担心会看不进去，但是后来发现完全没有这个担心。剧情还是挺幽默也挺温馨的，每个角色的性格刻画也非常有个性，非常有正能量的故事~最后一群愤怒的小鸟崛起的时候，莫名热血！PS:崽子辣么萌，我能抱走一个吗？
        public String content;
        public String time;//2016-05-23 22:21
    }
}
