package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/18.
 */

public class CityBean {

    /**
     * id : 12606
     * name : 下城区
     * level : 3
     * parent_id : 12597
     * children : [{"id":"12600","name":"清波街道","level":"4","parent_id":"12599"},{"id":"12601","name":"湖滨街道","level":"4","parent_id":"12599"},{"id":"12602","name":"小营街道","level":"4","parent_id":"12599"},{"id":"12603","name":"南星街道","level":"4","parent_id":"12599"},{"id":"12604","name":"紫阳街道","level":"4","parent_id":"12599"},{"id":"12605","name":"望江街道","level":"4","parent_id":"12599"}]
     */

    private String id;
    private String name;
    private String level;
    private String parent_id;
    private List<ChildrenBean> children;

    public CityBean(String id, String name, List<ChildrenBean> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * id : 12600
         * name : 清波街道
         * level : 4
         * parent_id : 12599
         */

        private String id;
        private String name;
        private String level;
        private String parent_id;

        public ChildrenBean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
