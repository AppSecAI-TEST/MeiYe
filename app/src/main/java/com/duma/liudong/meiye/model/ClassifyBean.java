package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/3/28.
 */

public class ClassifyBean {


    /**
     * id : 1
     * name : 彩妆/香水/美妆工具
     * image : http://www.meiye.com/Public/upload/category/2017/03-27/58d8d113e8a3d.jpeg
     * goods_id : 5
     * goods_url : http://www.meiye.com/goods.html?id=5
     * second : [{"id":"2","name":"指甲油/美甲产品","image":"","three":[{"id":"3","name":"指甲彩妆","image":""}]}]
     */

    private boolean click = false;
    private String id;
    private String name;
    private String image;
    private String goods_id;
    private String goods_num;
    private String goods_url;

    public ClassifyBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<SecondBean> second;

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public List<SecondBean> getSecond() {
        return second;
    }

    public void setSecond(List<SecondBean> second) {
        this.second = second;
    }

    public static class SecondBean {
        /**
         * id : 2
         * name : 指甲油/美甲产品
         * image :
         * three : [{"id":"3","name":"指甲彩妆","image":""}]
         */

        private String id;
        private String name;
        private String image;
        private List<ThreeBean> three;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<ThreeBean> getThree() {
            return three;
        }

        public void setThree(List<ThreeBean> three) {
            this.three = three;
        }

        public static class ThreeBean {
            /**
             * id : 3
             * name : 指甲彩妆
             * image :
             */

            private String id;
            private String name;
            private String image;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
