package com.justdo.kangxidaojia.util;

/**
 * Created by Administrator on 2017/5/18.
 */

public class UrlUtil {
    //图片访问地址
    public static final String BASE_PIC_URL = "http://kxdj.kang1.com";
   // public static final String BASE_PIC_URL = "http://106.3.242.2:81";
    public static final String BASE_URL     = "http://kxdj.kang1.com/api_tv/";
   // public static final String BASE_URL     = "http://106.3.242.2:81/api_tv/";
    //广告地址
    public static final String AD_URL       = BASE_URL + "getAd?sId=%s&type=%s&mId=%s";
    public static final String AD_URL_TOW       = BASE_URL + "getAd?type=%s&mId=%s";
    //项目链表
    public static final String PROJECT_URL  = BASE_URL + "getProductByType?sId=%s&mId=%s";
    public static final String PROJECT_URL_TOW  = BASE_URL + "getProductByType?mId=%s";
    //项目详情
    public static final String DETAIL_URL   = BASE_URL + "getProductById?pId=%s&sId=%s&mId=%s";
    public static final String DETAIL_URL_TOW   = BASE_URL + "getProductById?pId=%s&mId=%s";
    //版本升级
    public static final String UP_DATA = BASE_URL + "getUpdate?ver=%s";
}
