package com.justdo.kangxidaojia.bean;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DetailBean {


    /**
     * data : {"address":"北京市大兴区西红门鸿坤理想城礼域府3号楼1单元302室","discPrice":"168.00","forHome":"0","forStore":"1","fullPrice":"188.00","id":"77","img1Url":"/upload/store/35/20170517133158291.jpg","oneword":"温阳补虚,通经活络,强身益寿","proName":"艾灸调理","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/checkout/s77","telphone":"010-80259181","timeLength":"60"}
     * msg : 操作成功!
     * status : 600
     */

    private DataBean data;
    private String msg;
    private int    status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * address : 北京市大兴区西红门鸿坤理想城礼域府3号楼1单元302室
         * discPrice : 168.00
         * forHome : 0
         * forStore : 1
         * fullPrice : 188.00
         * id : 77
         * img1Url : /upload/store/35/20170517133158291.jpg
         * oneword : 温阳补虚,通经活络,强身益寿
         * proName : 艾灸调理
         * qrcodeUrl : http://kxdj.kang1.com/client/#/tab/checkout/s77
         * telphone : 010-80259181
         * timeLength : 60
         */

        private String address;
        private String discPrice;
        private String forHome;
        private String forStore;
        private String fullPrice;
        private String id;
        private String img1Url;
        private String oneword;
        private String proName;
        private String qrcodeUrl;
        private String telphone;
        private String timeLength;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDiscPrice() {
            return discPrice;
        }

        public void setDiscPrice(String discPrice) {
            this.discPrice = discPrice;
        }

        public String getForHome() {
            return forHome;
        }

        public void setForHome(String forHome) {
            this.forHome = forHome;
        }

        public String getForStore() {
            return forStore;
        }

        public void setForStore(String forStore) {
            this.forStore = forStore;
        }

        public String getFullPrice() {
            return fullPrice;
        }

        public void setFullPrice(String fullPrice) {
            this.fullPrice = fullPrice;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg1Url() {
            return img1Url;
        }

        public void setImg1Url(String img1Url) {
            this.img1Url = img1Url;
        }

        public String getOneword() {
            return oneword;
        }

        public void setOneword(String oneword) {
            this.oneword = oneword;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getQrcodeUrl() {
            return qrcodeUrl;
        }

        public void setQrcodeUrl(String qrcodeUrl) {
            this.qrcodeUrl = qrcodeUrl;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getTimeLength() {
            return timeLength;
        }

        public void setTimeLength(String timeLength) {
            this.timeLength = timeLength;
        }
    }
}
