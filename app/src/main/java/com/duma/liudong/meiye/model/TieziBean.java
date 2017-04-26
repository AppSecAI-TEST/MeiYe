package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/11.
 */

public class TieziBean {

    /**
     * head_pic : http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-25/58fefd7592030.jpg
     * bbs_id : 17
     * title :
     * content : 点赞
     * add_time : 2017-04-25
     * click_count : 4
     * img_json : ["http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-25/58ff01c4434e1.jpg"]
     * user_id : 11
     * user_name : 晓得了
     * like_count : 1
     * comment_count : 0
     * bbs_comment : [{"bbs_comment_id":"55","bbs_id":"17","user_id":"11","user_name":"晓得了","content":"囧","reply_id":"0","reply_name":"","add_time":"2017-04-25 15:59:59","like_count":"0","head_pic":"http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-25/58fefd7592030.jpg"}]
     * is_follow : 0
     * is_like : 0
     */

    private String head_pic;
    private String bbs_id;
    private String title;
    private String content;
    private String add_time;
    private String click_count;
    private String user_id;
    private String user_name;
    private String like_count;
    private String comment_count;
    private int is_follow;
    private int is_like;
    private List<String> img_json;
    private List<BbsCommentBean> bbs_comment;

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getBbs_id() {
        return bbs_id;
    }

    public void setBbs_id(String bbs_id) {
        this.bbs_id = bbs_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getClick_count() {
        return click_count;
    }

    public void setClick_count(String click_count) {
        this.click_count = click_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public List<String> getImg_json() {
        return img_json;
    }

    public void setImg_json(List<String> img_json) {
        this.img_json = img_json;
    }

    public List<BbsCommentBean> getBbs_comment() {
        return bbs_comment;
    }

    public void setBbs_comment(List<BbsCommentBean> bbs_comment) {
        this.bbs_comment = bbs_comment;
    }

    public static class BbsCommentBean {
        /**
         * bbs_comment_id : 55
         * bbs_id : 17
         * user_id : 11
         * user_name : 晓得了
         * content : 囧
         * reply_id : 0
         * reply_name :
         * add_time : 2017-04-25 15:59:59
         * like_count : 0
         * head_pic : http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-25/58fefd7592030.jpg
         */

        private String bbs_comment_id;
        private String bbs_id;
        private String user_id;
        private String user_name;
        private String content;
        private String reply_id;
        private String reply_name;
        private String add_time;
        private String like_count;
        private String head_pic;

        public String getBbs_comment_id() {
            return bbs_comment_id;
        }

        public void setBbs_comment_id(String bbs_comment_id) {
            this.bbs_comment_id = bbs_comment_id;
        }

        public String getBbs_id() {
            return bbs_id;
        }

        public void setBbs_id(String bbs_id) {
            this.bbs_id = bbs_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReply_id() {
            return reply_id;
        }

        public void setReply_id(String reply_id) {
            this.reply_id = reply_id;
        }

        public String getReply_name() {
            return reply_name;
        }

        public void setReply_name(String reply_name) {
            this.reply_name = reply_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getLike_count() {
            return like_count;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }
    }
}
