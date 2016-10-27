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

}
