package com.duma.liudong.meiye.model;

import java.io.Serializable;

/**
 * Created by liudong on 17/4/11.
 */

public class ForumBean implements Serializable{

    /**
     * cat_id : 1
     * cat_name : 招聘专区
     * cat_type : 0
     * parent_id : 0
     * show_in_nav : 1
     * sort_order : 0
     * cat_desc :
     * keywords :
     * cat_alias :
     */

    private String cat_id;
    private String cat_name;
    private String cat_type;
    private String parent_id;
    private String show_in_nav;
    private String sort_order;
    private String cat_desc;
    private String keywords;
    private String cat_alias;

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

    public String getCat_type() {
        return cat_type;
    }

    public void setCat_type(String cat_type) {
        this.cat_type = cat_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getShow_in_nav() {
        return show_in_nav;
    }

    public void setShow_in_nav(String show_in_nav) {
        this.show_in_nav = show_in_nav;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getCat_desc() {
        return cat_desc;
    }

    public void setCat_desc(String cat_desc) {
        this.cat_desc = cat_desc;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCat_alias() {
        return cat_alias;
    }

    public void setCat_alias(String cat_alias) {
        this.cat_alias = cat_alias;
    }
}
