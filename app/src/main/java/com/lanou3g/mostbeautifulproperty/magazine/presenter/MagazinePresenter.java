package com.lanou3g.mostbeautifulproperty.magazine.presenter;

import com.lanou3g.mostbeautifulproperty.bean.MagazineLiteBean;
import com.lanou3g.mostbeautifulproperty.magazine.model.IMagazineModel;
import com.lanou3g.mostbeautifulproperty.magazine.model.MagazineModeImpl;
import com.lanou3g.mostbeautifulproperty.magazine.model.OnFinishedMagazineListener;
import com.lanou3g.mostbeautifulproperty.magazine.uiview.IMagazineView;

import java.util.List;

/**
 *
 */

public class MagazinePresenter {
    // P层需要有Model和View层的引用
    private IMagazineModel<MagazineLiteBean> mModel;
    private IMagazineView<MagazineLiteBean> mView;

    public MagazinePresenter(IMagazineView<MagazineLiteBean> view) {
        mView = view;
        mModel = new MagazineModeImpl();
    }

    public void startRequest(String strUrl){
        mView.showDialog();
        mModel.startRequest(strUrl, new OnFinishedMagazineListener<MagazineLiteBean>() {

            @Override
            public void onFinished(List<MagazineLiteBean> list) {
                mView.dismissDialog();
                mView.onResponse(list);
                mModel.deleteInfoDB(MagazineLiteBean.class);
                mModel.insertInfoDB(list);
            }

            @Override
            public void onError() {
                queryDB();
            }
        });
    }

    private void queryDB() {
        mModel.queryGankAll(new OnFinishedMagazineListener<MagazineLiteBean>() {

            @Override
            public void onFinished(List<MagazineLiteBean> list) {
                mView.onResponse(list);
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
