package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import java.util.List;

/**
 *
 */

public interface IMagazineView<T> {
    void showDialog();
    void dismissDialog();
    void onResponse(List<T> tList);
    void onError();
}
