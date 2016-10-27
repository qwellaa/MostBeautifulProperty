package com.lanou3g.mostbeautifulproperty.discover.discoverpresenter;

import com.lanou3g.mostbeautifulproperty.bean.PopupwindowBean;
import com.lanou3g.mostbeautifulproperty.discover.model.DiscoverModelmpl;
import com.lanou3g.mostbeautifulproperty.discover.model.IDiscoverModel;
import com.lanou3g.mostbeautifulproperty.discover.model.OnFinishDiscoverListener;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;

/**
 * Created by dllo on 16/10/26.
 */

public class DiscoverPresenter {
    private IDiscoverView<PopupwindowBean> mIDiscoverView;
    private IDiscoverModel<PopupwindowBean> mDiscoverModel;


    public DiscoverPresenter(IDiscoverView<PopupwindowBean> view) {
        mIDiscoverView = view;
        mDiscoverModel = new DiscoverModelmpl();


    }
    public void startRequest(String strUrl){
        mDiscoverModel.startRequest(strUrl, new OnFinishDiscoverListener<PopupwindowBean>() {
            @Override
            public void onFinished(PopupwindowBean popupwindowBean) {
                mIDiscoverView.onResponse(popupwindowBean);

            }

            @Override
            public void onError() {

            }
        });


    }

}
