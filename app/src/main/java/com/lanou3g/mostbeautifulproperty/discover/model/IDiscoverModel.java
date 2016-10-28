package com.lanou3g.mostbeautifulproperty.discover.model;

/**
 *
 */

public interface IDiscoverModel {
    <T>void startRequest(String urlStr,Class<T>tClass,OnFinishDiscoverListener<T> listener);

    <T>void insertInfoDB(T t);
    <T>void queryGankAll(OnFinishDiscoverListener<T> listener);
}
