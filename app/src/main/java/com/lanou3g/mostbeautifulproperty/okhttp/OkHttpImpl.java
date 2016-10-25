package com.lanou3g.mostbeautifulproperty.okhttp;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */

public class OkHttpImpl implements IHttpRequest{

    private OkHttpClient mClient;
    private Gson mGson;
    private Handler mHandler;

    public OkHttpImpl() {
        // 初始化handler, 并且规定handler在主线程
        mHandler = new Handler(Looper.getMainLooper());
        File file = Environment.getDownloadCacheDirectory();
        mClient = new OkHttpClient.Builder()
//                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cache(new Cache(file, 10 * 1024 * 1024))
                .build();
        mGson = new Gson();
    }

    @Override
    public <T> void getRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener) {
        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        asyncRequest(clazz, listener, request);
    }

    @Override
    public <T> void getRequest(String urlStr, Map<String, String> headers, Class<T> clazz, OnCompletedListener<T> listener) {
        Request request = new Request.Builder()
                .headers(Headers.of(headers))
                .url(urlStr)
                .build();
        asyncRequest(clazz, listener, request);
    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        FormBody body = getFormBody(requestBody);
        Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .build();
        asyncRequest(clazz, listener, request);
    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        FormBody body = getFormBody(requestBody);
        Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .headers(Headers.of(headers))
                .build();
        asyncRequest(clazz, listener, request);
    }

    @NonNull
    private FormBody getFormBody(Map<String, String> requestBody) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : requestBody.keySet()) {
            builder.add(key, requestBody.get(key));
        }
        return builder.build();
    }

    private <T> void postError(final OnCompletedListener<T> listener) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailed();
            }
        });
    }

    private <T> void postResponse(final T result, final OnCompletedListener<T> listener) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onCompleted(result);
            }
        });
    }

    private <T> void asyncRequest(final Class<T> clazz, final OnCompletedListener<T> listener, Request request) {
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                postError(listener);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final T result = mGson.fromJson(response.body().string(), clazz);
                    postResponse(result, listener);
                } else {
                    postError(listener);
                }
            }
        });
    }
}
