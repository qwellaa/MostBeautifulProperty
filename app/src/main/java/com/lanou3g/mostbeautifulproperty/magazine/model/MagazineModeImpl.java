package com.lanou3g.mostbeautifulproperty.magazine.model;

import com.lanou3g.mostbeautifulproperty.bean.MagazineBean;
import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 *
 */

public class MagazineModeImpl implements IMagazineModel<MagazineBean>{

    @Override
    public void startRequest(String urlStr, final OnFinishedMagazineListener<MagazineBean> listener) {
        HttpManager.getInstance().getRequest(urlStr, MagazineBean.class, new OnCompletedListener<MagazineBean>() {
            @Override
            public void onCompleted(MagazineBean result) {
                if (result.getResult() == 1) {
                    listener.onFinished(result);
                } else {
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
    public void insertInfoDB(MagazineBean magazineBean) {

    }

    @Override
    public void queryGankAll(OnFinishedMagazineListener<MagazineBean> listener) {

    }
}
