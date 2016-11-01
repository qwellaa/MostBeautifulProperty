package com.lanou3g.mostbeautifulproperty.magazine.model;

import java.util.List;

/**
 *
 */

public interface OnFinishedMagazineListener<T> {
    void onFinished(List<T> list);
    void onError();
}
