package com.lanou3g.mostbeautifulproperty.discover.model;

import com.lanou3g.mostbeautifulproperty.bean.DiscoverBean;
import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/10/27.
 */

public class DiscoverReuseModeImpl implements IDiscoverModel<DiscoverBean> {
    @Override
    public void startRequest(String urlStr, final OnFinishDiscoverListener<DiscoverBean> listener) {
        HttpManager.getInstance().getRequest(urlStr, DiscoverBean.class, new OnCompletedListener<DiscoverBean>() {
            @Override
            public void onCompleted(DiscoverBean result) {
                if (result.getResult() == 1){
                    listener.onFinished(result);
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
    public void insertInfoDB(DiscoverBean discoverBean) {

    }

    @Override
    public void queryGankAll(OnFinishDiscoverListener<DiscoverBean> listener) {

    }
}
