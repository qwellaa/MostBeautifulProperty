package com.lanou3g.mostbeautifulproperty.magazine.model;

/**
 *
 */

public interface IMagazineModel<T> {
    void startRequest(String urlStr, OnFinishedMagazineListener<T> listener);
    void insertInfoDB(T t);
    void queryGankAll(OnFinishedMagazineListener<T> listener);
}
