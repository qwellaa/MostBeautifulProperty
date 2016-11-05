package com.lanou3g.mostbeautifulproperty.okhttp;

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

}
