package com.lanou3g.mostbeautifulproperty.designer.model;

/**
 *
 */

public interface OnFinishDesignerListener<T> {
    void onFinished(T t);
    void onError();
}
