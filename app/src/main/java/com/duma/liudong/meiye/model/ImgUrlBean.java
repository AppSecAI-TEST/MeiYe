package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/4/11.
 */

public class ImgUrlBean {

    /**
     * status : 1
     * msg : 操作成功
     * result : {"path":"/Public/upload/head_pic/2017-04-11/58eca0deed4cc.jpg","url":"http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-11/58eca0deed4cc.jpg"}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * path : /Public/upload/head_pic/2017-04-11/58eca0deed4cc.jpg
         * url : http://meiye.duma-ivy.cn/Public/upload/head_pic/2017-04-11/58eca0deed4cc.jpg
         */

        private String path;
        private String url;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
