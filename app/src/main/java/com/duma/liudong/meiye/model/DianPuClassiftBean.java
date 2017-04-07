package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/7.
 */

public class DianPuClassiftBean {

    /**
     * cat_id : 1
     * cat_name : 店铺分类一
     * parent_id : 0
     * store_id : 1
     * cat_sort : 0
     * is_show : 1
     * is_nav_show : 1
     * is_recommend : 0
     * show_num : 0
     * children : [{"cat_id":"5","cat_name":"测试一","parent_id":"1","store_id":"1","cat_sort":"0","is_show":"1","is_nav_show":"1","is_recommend":"1","show_num":"0"}]
     */

    private String cat_id;
    private String cat_name;
    private String parent_id;
    private String store_id;
    private String cat_sort;
    private String is_show;
    private String is_nav_show;
    private String is_recommend;
    private String show_num;
    private List<ChildrenBean> children;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCat_sort() {
        return cat_sort;
    }

    public void setCat_sort(String cat_sort) {
        this.cat_sort = cat_sort;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getIs_nav_show() {
        return is_nav_show;
    }

    public void setIs_nav_show(String is_nav_show) {
        this.is_nav_show = is_nav_show;
    }

    public String getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(String is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getShow_num() {
        return show_num;
    }

    public void setShow_num(String show_num) {
        this.show_num = show_num;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * cat_id : 5
         * cat_name : 测试一
         * parent_id : 1
         * store_id : 1
         * cat_sort : 0
         * is_show : 1
         * is_nav_show : 1
         * is_recommend : 1
         * show_num : 0
         */

        private String cat_id;
        private String cat_name;
        private String parent_id;
        private String store_id;
        private String cat_sort;
        private String is_show;
        private String is_nav_show;
        private String is_recommend;
        private String show_num;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getCat_sort() {
            return cat_sort;
        }

        public void setCat_sort(String cat_sort) {
            this.cat_sort = cat_sort;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getIs_nav_show() {
            return is_nav_show;
        }

        public void setIs_nav_show(String is_nav_show) {
            this.is_nav_show = is_nav_show;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getShow_num() {
            return show_num;
        }

        public void setShow_num(String show_num) {
            this.show_num = show_num;
        }
    }
}
