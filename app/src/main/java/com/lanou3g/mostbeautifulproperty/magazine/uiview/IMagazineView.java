package com.lanou3g.mostbeautifulproperty.magazine.uiview;

/**
 *
 */

public interface IMagazineView<T> {
    void showDialog();
    void dismissDialog();
    void onResponse(T t);
    void onError();
}
