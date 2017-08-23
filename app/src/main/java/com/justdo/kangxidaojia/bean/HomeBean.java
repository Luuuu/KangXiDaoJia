package com.justdo.kangxidaojia.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class HomeBean {


    /**
     * data : [{"forHome":"0","forStore":"1","fullPrice":"150.00","id":"83","imgUrl":"/upload/service/1/20170605144100615.jpg","proName":"头颈肩调理","proPrice":"128.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s83","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"45"},{"forHome":"0","forStore":"1","fullPrice":"215.00","id":"81","imgUrl":"/upload/service/1/20170605144110134.jpg","proName":"全身经络调理 90分钟","proPrice":"198.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s81","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"90"},{"forHome":"1","forStore":"0","fullPrice":"288.00","id":"79","imgUrl":"/upload/service/1/20170605144119285.jpg","proName":"全身经络调理 90分钟","proPrice":"268.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h79","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"90"},{"forHome":"0","forStore":"1","fullPrice":"188.00","id":"77","imgUrl":"/upload/service/1/20170605144136896.jpg","proName":"艾灸调理","proPrice":"168.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s77","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"60"},{"forHome":"1","forStore":"0","fullPrice":"215.00","id":"75","imgUrl":"/upload/service/1/20170605144335229.jpg","proName":"艾灸调理","proPrice":"198.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h75","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"60"},{"forHome":"0","forStore":"1","fullPrice":"188.00","id":"73","imgUrl":"/upload/store/35/20170601155012681.png","proName":"全身经络调理 60分钟","proPrice":"168.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s73","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"60"},{"forHome":"1","forStore":"0","fullPrice":"235.00","id":"71","imgUrl":"/upload/service/1/20170605144358770.jpg","proName":"全身经络调理 60分钟","proPrice":"218.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h71","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"60"},{"forHome":"1","forStore":"0","fullPrice":"198.00","id":"69","imgUrl":"/upload/service/1/20170605144407390.jpg","proName":"头颈肩调理","proPrice":"168.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h69","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"45"},{"forHome":"0","forStore":"1","fullPrice":"315.00","id":"67","imgUrl":"/upload/service/1/20170605144423477.jpg","proName":"女性乳腺保养","proPrice":"298.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s67","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"45"},{"forHome":"1","forStore":"0","fullPrice":"420.00","id":"65","imgUrl":"/upload/service/1/20170605144432192.jpg","proName":"女性乳腺保养","proPrice":"398.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h65","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"45"},{"forHome":"0","forStore":"1","fullPrice":"128.00","id":"63","imgUrl":"/upload/service/1/20170605101758322.png","proName":"小儿推拿","proPrice":"108.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/s63","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"30"},{"forHome":"1","forStore":"0","fullPrice":"150.00","id":"61","imgUrl":"/upload/service/1/20170605100249491.png","proName":"小儿推拿","proPrice":"128.00","qrcodeUrl":"http://kxdj.kang1.com/client/#/tab/details/h61","qrpos":"rightTop","qrsize":"200","storeId":"35","timeLength":"30"}]
     * msg : 民政局指定为老服务机构
     * status : 200
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
         * forHome : 0
         * forStore : 1
         * fullPrice : 150.00
         * id : 83
         * imgUrl : /upload/service/1/20170605144100615.jpg
         * proName : 头颈肩调理
         * proPrice : 128.00
         * qrcodeUrl : http://kxdj.kang1.com/client/#/tab/details/s83
         * qrpos : rightTop
         * qrsize : 200
         * storeId : 35
         * timeLength : 45
         */

        private String forHome;
        private String forStore;
        private String fullPrice;
        private String id;
        private String imgUrl;
        private String proName;
        private String proPrice;
        private String qrcodeUrl;
        private String qrpos;
        private String qrsize;
        private String storeId;
        private String timeLength;

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getProPrice() {
            return proPrice;
        }

        public void setProPrice(String proPrice) {
            this.proPrice = proPrice;
        }

        public String getQrcodeUrl() {
            return qrcodeUrl;
        }

        public void setQrcodeUrl(String qrcodeUrl) {
            this.qrcodeUrl = qrcodeUrl;
        }

        public String getQrpos() {
            return qrpos;
        }

        public void setQrpos(String qrpos) {
            this.qrpos = qrpos;
        }

        public String getQrsize() {
            return qrsize;
        }

        public void setQrsize(String qrsize) {
            this.qrsize = qrsize;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getTimeLength() {
            return timeLength;
        }

        public void setTimeLength(String timeLength) {
            this.timeLength = timeLength;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "forHome='" + forHome + '\'' +
                    ", forStore='" + forStore + '\'' +
                    ", fullPrice='" + fullPrice + '\'' +
                    ", id='" + id + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", proName='" + proName + '\'' +
                    ", proPrice='" + proPrice + '\'' +
                    ", qrcodeUrl='" + qrcodeUrl + '\'' +
                    ", qrpos='" + qrpos + '\'' +
                    ", qrsize='" + qrsize + '\'' +
                    ", storeId='" + storeId + '\'' +
                    ", timeLength='" + timeLength + '\'' +
                    '}';
        }
    }
}
