package com.lanou3g.mostbeautifulproperty.okhttp;

import java.util.Map;

/**
 *
 */

public interface IHttpRequest {
    /**
     * 普通的Get请求
     * @param urlStr : 网址
     * @param clazz : 解析的实体类结果
     * @param listener : 网络请求的结果回调
     * @param <T> : 解析的实体类类型
     */
    <T> void getRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * 带请求头的Get请求
     * @param urlStr : 网址
     * @param headers : 请求头
     * @param clazz : 解析的实体类结果
     * @param listener : 网络请求的结果回调
     * @param <T> : 解析的实体类类型
     */
    <T> void getRequest(String urlStr, Map<String, String> headers, Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * post请求
     * @param urlStr : 网址
     * @param requestBody : 请求体
     * @param clazz : 解析的实体类结果
     * @param listener : 网络请求的结果回调
     * @param <T> : 解析的实体类类型
     */
    <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * 带请求头的post请求
     * @param urlStr : 网址
     * @param headers : 请求头
     * @param requestBody : 请求体
     * @param clazz : 解析的实体类结果
     * @param listener : 网络请求的结果回调
     * @param <T> : 解析的实体类类型
     */
    <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener);
}
