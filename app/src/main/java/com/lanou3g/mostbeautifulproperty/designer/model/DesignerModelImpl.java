package com.lanou3g.mostbeautifulproperty.designer.model;

import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 *
 */

public class DesignerModelImpl implements IDesignerModel{


    @Override
    public <T> void startRequest(String urlStr, Class<T> clazz, final OnCompletedListener<T> listener) {
        HttpManager.getInstance().getRequest(urlStr, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                listener.onCompleted(result);
            }

            @Override
            public void onFailed() {

            }
        });

    }

    @Override
    public <T> void queryGankAll(OnCompletedListener<T> listener) {

    }

    @Override
    public <T> void insertIntoDB(T t) {

    }
}
