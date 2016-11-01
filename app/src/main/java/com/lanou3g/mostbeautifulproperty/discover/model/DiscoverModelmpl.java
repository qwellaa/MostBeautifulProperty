package com.lanou3g.mostbeautifulproperty.discover.model;

import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/10/26.
 */

public class DiscoverModelmpl implements IDiscoverModel{
    private static final  int REQUEST_CODE = 1;

    @Override
    public <T> void startRequest(String urlStr, Class<T> tClass, final OnFinishDiscoverListener<T> listener) {
        HttpManager.getInstance().getRequest(urlStr, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                listener.onFinished( result);
            }

            @Override
            public void onFailed() {
                listener.onError();
            }
        });
    }

    @Override
    public <T> void insertInfoDB(T t) {

    }

    @Override
    public <T> void queryGankAll(OnFinishDiscoverListener<T> listener) {

    }
}
