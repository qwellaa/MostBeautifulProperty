package com.lanou3g.mostbeautifulproperty.designer.presenter;

import com.lanou3g.mostbeautifulproperty.designer.model.DesignerModelImpl;
import com.lanou3g.mostbeautifulproperty.designer.model.IDesignerModel;
import com.lanou3g.mostbeautifulproperty.designer.uiview.IDesignerView;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 *
 */

public class DesignerPresenter<T> {
    private IDesignerView mView;
    private IDesignerModel mModel;

    public DesignerPresenter(IDesignerView view) {
        mView = view;
        mModel = new DesignerModelImpl();
    }

    public void startRequest(String urlStr, Class<T> clazz) {
         mView.showDialog();
        mModel.startRequest(urlStr, clazz, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
              mView.dismissDialog();
                mView.onResponse(result);
            }

            @Override
            public void onFailed() {
                mView.onError();
                mView.dismissDialog();
            }
        });
    }

    private void queryDB(){
        mModel.queryGankAll(new OnCompletedListener<Object>() {
            @Override
            public void onCompleted(Object result) {


            }

            @Override
            public void onFailed() {

            }
        });
    }

}
