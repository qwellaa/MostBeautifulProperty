package com.lanou3g.mostbeautifulproperty.magazine.model;

import java.util.List;

/**
 *
 */

public interface IMagazineModel<T> {
    void startRequest(String urlStr, OnFinishedMagazineListener<T> listener);
    void insertInfoDB(List<T> list);
    void queryGankAll(OnFinishedMagazineListener<T> listener);
    void deleteInfoDB(Class<T> tClass);
}
