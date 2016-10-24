package com.lanou3g.mostbeautifulproperty.magazine.model;

/**
 *
 */

public interface OnFinishedMagazineListener<T> {
    void onFinished(T t);
    void onError();
}
