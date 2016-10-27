package com.lanou3g.mostbeautifulproperty.designer.model;

import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 *
 */

public class DesignerModelImpl implements IDesignerModel{


    @Override
    public <T> void startRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener) {
        HttpManager.getInstance().getRequest(urlStr, clazz, listener);
    }

    @Override
    public <T> void queryGankAll(OnCompletedListener<T> listener) {

    }

    @Override
    public <T> void insertIntoDB(T t) {

    }
}
