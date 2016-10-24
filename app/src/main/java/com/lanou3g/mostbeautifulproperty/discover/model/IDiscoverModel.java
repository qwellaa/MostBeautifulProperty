package com.lanou3g.mostbeautifulproperty.discover.model;

/**
 *
 */

public interface IDiscoverModel<T> {
    void startRequest(String urlStr, OnFinishDiscoverListener<T> listener);
    void insertInfoDB(T t);
    void queryGankAll(OnFinishDiscoverListener<T> listener);
}
