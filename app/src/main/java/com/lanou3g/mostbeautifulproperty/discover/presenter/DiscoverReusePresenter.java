package com.lanou3g.mostbeautifulproperty.discover.presenter;

import com.lanou3g.mostbeautifulproperty.bean.DiscoverBean;
import com.lanou3g.mostbeautifulproperty.discover.model.DiscoverReuseModeImpl;
import com.lanou3g.mostbeautifulproperty.discover.model.IDiscoverModel;
import com.lanou3g.mostbeautifulproperty.discover.model.OnFinishDiscoverListener;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;

/**
 * Created by dllo on 16/10/27.
 */

public class DiscoverReusePresenter {
    //调用M,V层
    private IDiscoverModel<DiscoverBean> mModel;
    private IDiscoverView<DiscoverBean> mView;

    public DiscoverReusePresenter(IDiscoverView<DiscoverBean> view) {
        mView = view;
        mModel = new DiscoverReuseModeImpl();
    }

    public void startRequest(String strUrl){
        mView.showDialog();
        mModel.startRequest(strUrl, new OnFinishDiscoverListener<DiscoverBean>() {
            @Override
            public void onFinished(DiscoverBean discoverBean) {
                mView.onResponse(discoverBean);
                mView.dismissDialog();
            }

            @Override
            public void onError() {
                mView.onError();
                mView.dismissDialog();
            }
        });
    }
    private void queryDB(){
        mModel.queryGankAll(new OnFinishDiscoverListener<DiscoverBean>() {
            @Override
            public void onFinished(DiscoverBean discoverBean) {
                mView.onResponse(discoverBean);
                mView.dismissDialog();
            }

            @Override
            public void onError() {
                mView.onError();
                mView.dismissDialog();
            }
        });
    }
}
