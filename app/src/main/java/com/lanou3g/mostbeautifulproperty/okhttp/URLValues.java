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



}
