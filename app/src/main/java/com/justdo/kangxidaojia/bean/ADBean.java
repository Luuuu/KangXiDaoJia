package com.justdo.kangxidaojia.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class ADBean {

    /**
     * data : [{"dispTime":"3","h5Url":"http://kxdj.kang1.com/admin/tv/adver_add/35","id":"8","state":"0","storeId":"35","thumbnailUrl":"/upload/tv/ad/20170516153457667.jpeg","title":"店铺开业"}]
     * msg : 操作成功!
     * status : 600
     */

    private String msg;
    private int            status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dispTime : 3
         * h5Url : http://kxdj.kang1.com/admin/tv/adver_add/35
         * id : 8
         * state : 0
         * storeId : 35
         * thumbnailUrl : /upload/tv/ad/20170516153457667.jpeg
         * title : 店铺开业
         */

        private String dispTime;
        private String h5Url;
        private String id;
        private String state;
        private String storeId;
        private String thumbnailUrl;
        private String title;

        public String getDispTime() {
            return dispTime;
        }

        public void setDispTime(String dispTime) {
            this.dispTime = dispTime;
        }

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "dispTime='" + dispTime + '\'' +
                    ", h5Url='" + h5Url + '\'' +
                    ", id='" + id + '\'' +
                    ", state='" + state + '\'' +
                    ", storeId='" + storeId + '\'' +
                    ", thumbnailUrl='" + thumbnailUrl + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
