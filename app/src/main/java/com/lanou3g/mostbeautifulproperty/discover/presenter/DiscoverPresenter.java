package com.lanou3g.mostbeautifulproperty.discover.presenter;

import com.lanou3g.mostbeautifulproperty.discover.model.DiscoverModelmpl;
import com.lanou3g.mostbeautifulproperty.discover.model.IDiscoverModel;
import com.lanou3g.mostbeautifulproperty.discover.model.OnFinishDiscoverListener;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;

/**
 * Created by dllo on 16/10/26.
 */

public class DiscoverPresenter{
    private IDiscoverView mIDiscoverView;
    private IDiscoverModel mDiscoverModel;


    public DiscoverPresenter(IDiscoverView view) {
        mIDiscoverView = view;
        mDiscoverModel = new DiscoverModelmpl();
    }



    public <T> void startRequest(String strUrl,Class<T>clazz){
        mIDiscoverView.showDialog();
        mDiscoverModel.startRequest(strUrl, clazz, new OnFinishDiscoverListener<T>() {
            @Override
            public void onFinished(T t) {
                mIDiscoverView.dismissDialog();
                mIDiscoverView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }
}
