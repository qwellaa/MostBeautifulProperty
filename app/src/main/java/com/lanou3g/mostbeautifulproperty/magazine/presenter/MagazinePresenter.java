package com.lanou3g.mostbeautifulproperty.magazine.presenter;

import com.lanou3g.mostbeautifulproperty.bean.MagazineBean;
import com.lanou3g.mostbeautifulproperty.magazine.model.IMagazineModel;
import com.lanou3g.mostbeautifulproperty.magazine.model.MagazineModeImpl;
import com.lanou3g.mostbeautifulproperty.magazine.model.OnFinishedMagazineListener;
import com.lanou3g.mostbeautifulproperty.magazine.uiview.IMagazineView;

/**
 *
 */

public class MagazinePresenter {
    // P层需要有Model和View层的引用
    private IMagazineModel<MagazineBean> mModel;
    private IMagazineView<MagazineBean> mView;

    public MagazinePresenter(IMagazineView<MagazineBean> view) {
        mView = view;
        mModel = new MagazineModeImpl();
    }

    public void startRequest(String strUrl){
        mView.showDialog();
        mModel.startRequest(strUrl, new OnFinishedMagazineListener<MagazineBean>() {
            @Override
            public void onFinished(MagazineBean magazineBean) {
                mView.dismissDialog();
                mView.onResponse(magazineBean);
//                mModel.insertInfoDB(magazineBean);
            }

            @Override
            public void onError() {
//                queryDB();
            }
        });
    }

    private void queryDB() {
        mModel.queryGankAll(new OnFinishedMagazineListener<MagazineBean>() {
            @Override
            public void onFinished(MagazineBean magazineBean) {
                mView.onResponse(magazineBean);
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
