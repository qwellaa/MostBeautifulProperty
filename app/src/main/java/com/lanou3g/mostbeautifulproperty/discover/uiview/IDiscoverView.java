package com.lanou3g.mostbeautifulproperty.discover.uiview;

/**
 *
 */

public interface IDiscoverView<T> {
    void showDialog();
    void dismissDialog();
    void onResponse(T t);
    void onError();
}
