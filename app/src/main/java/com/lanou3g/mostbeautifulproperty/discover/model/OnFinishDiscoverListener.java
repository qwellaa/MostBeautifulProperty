package com.lanou3g.mostbeautifulproperty.discover.model;

/**
 *
 */

public interface OnFinishDiscoverListener<T> {
    void onFinished(T t);
    void onError();
}
