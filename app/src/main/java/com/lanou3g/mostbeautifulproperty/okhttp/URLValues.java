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

    //第二页 首饰 全部
    public static final String DISCOVER_JEWELRY_ALL_URL = "http://design.zuimeia.com/api/v1/products/category/3/?page=1&page_size=30&device_id=862638038074682&platform=android&lang=zh&appVersion=1.2.2&appVersionCode=10220&systemVersion=23&countryCode=CN&user_id=0&token=&package_name=com.zuiapps.zuiworld";

}
