package com.lanou3g.mostbeautifulproperty.designer.model;

import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 *
 */

public interface IDesignerModel {
    <T> void startRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener);
    <T> void queryGankAll(OnCompletedListener<T> listener);
    <T> void insertIntoDB(T t);
}
