package com.lanou3g.mostbeautifulproperty.discover.presenter;

/**
 * Created by dllo on 16/10/27.
 */

public class DiscoverReusePresenter<T> {
    //调用M,V层
//    private IDiscoverModel mModel;
//    private IDiscoverView mView;
//
//    public DiscoverReusePresenter(IDiscoverView view) {
//        mView = view;
//        mModel = new DiscoverReuseModeImpl();
//    }
//
//    public void startRequest(String strUrl,Class<T>clazz){
//        mView.showDialog();
//        mModel.startRequest(strUrl,clazz, new OnFinishDiscoverListener<T>() {
//            @Override
//            public void onFinished(T t) {
//                mView.onResponse(t);
//                mView.dismissDialog();
//            }
//
//            @Override
//            public void onError() {
//                mView.onError();
//                mView.dismissDialog();
//            }
//        });
//    }
//    private void queryDB(){
//        mModel.queryGankAll(new OnFinishDiscoverListener<DiscoverBean>() {
//            @Override
//            public void onFinished(DiscoverBean discoverBean) {
//                mView.onResponse(discoverBean);
//                mView.dismissDialog();
//            }
//
//            @Override
//            public void onError() {
//                mView.onError();
//                mView.dismissDialog();
//            }
//        });
//    }
}
