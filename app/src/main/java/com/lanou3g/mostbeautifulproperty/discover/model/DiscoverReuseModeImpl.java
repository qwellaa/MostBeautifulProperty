package com.lanou3g.mostbeautifulproperty.discover.model;

import com.lanou3g.mostbeautifulproperty.bean.DiscoverBean;
import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/10/27.
 */

public class DiscoverReuseModeImpl implements IDiscoverModel {

    @Override
    public <T> void startRequest(String urlStr, Class<T> tClass, final OnFinishDiscoverListener<T> listener) {
        HttpManager.getInstance().getRequest(urlStr, DiscoverBean.class, new OnCompletedListener<DiscoverBean>() {
            @Override
            public void onCompleted(DiscoverBean result) {
                if (1 == result.getResult()){
                    listener.onFinished((T) result);
                }else {
                    listener.onError();
                    return;
                }
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
