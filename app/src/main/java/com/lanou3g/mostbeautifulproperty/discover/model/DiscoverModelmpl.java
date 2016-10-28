package com.lanou3g.mostbeautifulproperty.discover.model;

import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/10/26.
 */

public class DiscoverModelmpl implements IDiscoverModel{
    private static final  int REQUEST_CODE = 1;

    @Override
    public <T> void startRequest(String urlStr, Class<T> tClass, final OnFinishDiscoverListener<T> listener) {
        HttpManager.getInstance().getRequest(urlStr, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {

                    listener.onFinished( result);


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

//    @Override
//    public void startRequest(String urlStr, final OnFinishDiscoverListener<PopupwindowBean> listener) {
//        HttpManager.getInstance().getRequest(urlStr, PopupwindowBean.class, new OnCompletedListener<PopupwindowBean>() {
//            @Override
//            public void onCompleted(PopupwindowBean result) {
//                if ( REQUEST_CODE == result.getResult()){
//                    listener.onFinished(result);
//                } else {
//                    listener.onError();
//                }
//
//            }
//
//            @Override
//            public void onFailed() {
//                listener.onError();
//
//            }
//        });
//
//    }
//
//    @Override
//    public void insertInfoDB(PopupwindowBean popupwindowBean) {
//
//    }
//
//    @Override
//    public void queryGankAll(OnFinishDiscoverListener<PopupwindowBean> listener) {
//
//    }
}
