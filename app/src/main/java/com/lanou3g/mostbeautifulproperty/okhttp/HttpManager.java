package com.lanou3g.mostbeautifulproperty.okhttp;

import java.util.Map;

/**
 *
 */

public class HttpManager implements IHttpRequest{

    private static final String TAG = "HttpManager";

    private static final class SingletonHolder{
        private static final HttpManager sInstance = new HttpManager();
    }

    public static HttpManager getInstance(){
        return SingletonHolder.sInstance;
    }

    private IHttpRequest mRequest;

    public HttpManager() {
        mRequest = new OkHttpImpl();
    }

    // get请求
    @Override
    public <T> void getRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.getRequest(urlStr, clazz, listener);
    }

    @Override
    public <T> void getRequest(String urlStr, Map<String, String> headers, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.getRequest(urlStr, headers, clazz, listener);
    }

    // post请求
    @Override
    public <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr, requestBody, clazz, listener);
    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr, headers, requestBody, clazz, listener);
    }
}
