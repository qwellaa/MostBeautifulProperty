package com.lanou3g.mostbeautifulproperty.okhttp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public final class URLValues {

    // 第一页 画报
    public static final String MAGAZINE_HEAD_URL = "http://design.zuimeia.com/api/v1/articles/daily/simple/?page=";
    public static final String MAGAZINE_TAIL_URL = "&is_new_user=false&page_size=";
    public static String getMagazineUrl(int page, int pageSize) {
        return MAGAZINE_HEAD_URL + page + MAGAZINE_TAIL_URL + pageSize;
    }

    // 第一页 画报详情
    private static final String MAGAZINE_DETAILS_TOP_URL = "http://design.zuimeia.com/api/v1/article/";
    private static final String MAGAZINE_DETAILS_BOTTOM_URL = "/";
    public static String getMAGAZINEDETAILS_URL(int id){
        return MAGAZINE_DETAILS_TOP_URL + id + MAGAZINE_DETAILS_BOTTOM_URL;
    }

    public static final String POPUPWINDOW_URL = "http://design.zuimeia.com/api/v1/product/categories/";

    // 有物 daily
    public static final String Daily_HEAD_URL = "http://design.zuimeia.com/api/v1/products/daily/?timestamp=";
    public static final String Daily_TAIL_URL = "000";
    public static String getDaily_HEAD_URL(Long time) {
        return Daily_HEAD_URL + time;
    }

    //第二页 全部
    public static final String DISCOVER_HEAD_URL = "http://design.zuimeia.com/api/v1/products/category/";
    public static final String DISCOVER_MID_URL = "/?page=";
    public static final String DISCOVER_TAIL_URL = "&page_size=";

    public static String getDISCOVER_URL(int category,int page,int size) {
        return DISCOVER_HEAD_URL + category + DISCOVER_MID_URL + page + DISCOVER_TAIL_URL + size;
    }

    public static String getDISCOVER_HEAD_AND_MID_URL(int category){
        return DISCOVER_HEAD_URL+ category + DISCOVER_MID_URL;
    }

    public static String getDISCOVER_TAIL_URL(){
        return DISCOVER_TAIL_URL;
    }

    // 最美有物下载地址
    public static final String APP_DOWNLOAD_URL = "http://design.zuimeia.com/app.html";
    //百思不得姐视频
    //?market=360zhushou&ver=6.5.7&visiting=19543442&os=5.1&appname=baisibudejie&client=android&udid=860954030358581&mac=74%3Aac%3A5f%3A6d%3A3c%3A93
    public static final String VIDEO_URL = "http://s.budejie.com/topic/list/jingxuan/41/budejie-android-6.5.7/0-20.json";
    public static final String VIDEO_TOP_URL = "http://s.budejie.com/topic/list/jingxuan/41/budejie-android-6.5.7/";
    public static final String VIDEO_BUTTOM_URL = "-20.json";
    public static String getVIDEO_URL ( int page){

        return VIDEO_TOP_URL + page + VIDEO_BUTTOM_URL;

        //http://c.api.budejie.com/topic/comment_list/21800460/0/budejie-android-6.5.7/0-20.json
        //http://c.api.budejie.com/topic/comment_list/21812462/0/budejie-android-6.5.7/0-20.json

    }

    public static final String VIDEO_DETAIL_UP_URL = "http://c.api.budejie.com/topic/comment_list/";
    public static final String VIDEO_DERAIL_DOWN_URL = "/0/budejie-android-6.5.7/0-20.json";
    public static final String getVideoDetailUrl (String page){

        return VIDEO_DETAIL_UP_URL + page + VIDEO_DERAIL_DOWN_URL;
    }

    private static final List<String> URL_JEWERY_LIST(){
        List<String>URL_JEWERY_LIST = new ArrayList<>();
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(3));
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(24));
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(23));
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(22));
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(21));
        URL_JEWERY_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(20));
        return URL_JEWERY_LIST;
    }

    private static final List<String> URL_BAG_LIST(){
        List<String>URL_BAG_LIST = new ArrayList<>();
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(1));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(51));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(32));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(10));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(9));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(8));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(7));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(6));
        URL_BAG_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(5));
        return URL_BAG_LIST;
    }

    private static final List<String> URL_SHOE_LIST(){
        List<String>URL_SHOE_LIST = new ArrayList<>();
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(2));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(14));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(49));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(48));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(38));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(16));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(15));
        URL_SHOE_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(11));
        return URL_SHOE_LIST;
    }

    private static final List<String> URL_MAN_LIST(){
        ArrayList<String>URL_MAN_LIST = new ArrayList<>();
        URL_MAN_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(65));
        return URL_MAN_LIST;
    }

    private static final List<String> URL_ACCESSORIES_LIST(){
        List<String>URL_ACCESSORIES_LIST = new ArrayList<>();
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(4));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(53));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(52));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(45));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(37));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(36));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(29));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(27));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(26));
        URL_ACCESSORIES_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(25));
        return URL_ACCESSORIES_LIST;
    }

    private static final List<String> URL_OTHER_LIST(){
        List<String>URL_OTHER_LIST = new ArrayList<>();
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(54));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(68));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(64));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(61));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(58));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(56));
        URL_OTHER_LIST.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(42));
        return URL_OTHER_LIST;
    }

    public static final List<List<String>>URL_HEAD_AND_MID(){
        List<List<String>>URL_HEAD_AND_MID = new ArrayList<>();
        URL_HEAD_AND_MID.add(URL_JEWERY_LIST());
        URL_HEAD_AND_MID.add(URL_BAG_LIST());
        URL_HEAD_AND_MID.add(URL_SHOE_LIST());
        URL_HEAD_AND_MID.add(URL_MAN_LIST());
        URL_HEAD_AND_MID.add(URL_ACCESSORIES_LIST());
        URL_HEAD_AND_MID.add(URL_OTHER_LIST());
        return URL_HEAD_AND_MID;
    }

}
