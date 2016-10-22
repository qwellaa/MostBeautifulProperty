package com.lanou3g.mostbeautifulproperty.bean;

import java.util.ArrayList;

/**
 *
 */

public final class DiscoverTitlesBean {

    private static final ArrayList<String> titles = new ArrayList<>();

    public static final ArrayList<String> getTitles() {
            titles.add("喜欢的");
            titles.add("设计师动态");
            titles.add("Daily");
            titles.add("首饰");
            titles.add("包袋");
            titles.add("鞋履");
            titles.add("Men");
            titles.add("配饰");
            titles.add("其他");
            return titles;
    }
}
