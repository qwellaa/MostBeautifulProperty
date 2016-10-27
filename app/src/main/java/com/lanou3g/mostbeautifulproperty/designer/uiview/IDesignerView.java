package com.lanou3g.mostbeautifulproperty.designer.uiview;

/**
 *
 */

public interface IDesignerView<T> {
    void showDialog();
    void dismissDialog();
    void onResponse(T t);
    void onError();
}
