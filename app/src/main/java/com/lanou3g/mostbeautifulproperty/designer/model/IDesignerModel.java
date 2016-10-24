package com.lanou3g.mostbeautifulproperty.designer.model;

import com.lanou3g.mostbeautifulproperty.discover.model.OnFinishDiscoverListener;

/**
 *
 */

public interface IDesignerModel<T> {
    void startRequest(String urlStr, OnFinishDiscoverListener<T> listener);
    void insertInfoDB(T t);
    void queryGankAll(OnFinishDiscoverListener<T> listener);
}
