package com.lanou3g.mostbeautifulproperty.okhttp;

/**
 *
 */

public interface OnCompletedListener<T> {
    void onCompleted(T result);
    void onFailed();
}
