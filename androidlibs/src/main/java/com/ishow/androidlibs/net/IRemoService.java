package com.ishow.androidlibs.net;

import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by huangxuanheng on 16-5-24.
 */
public interface IRemoService {
        /**
         * get 请求，请求结果将在callback中回调
         * @param url 请求接口地址
         * @param callback 回调接口
         * @param <T> 回调接口返回的数据json对应对象
         */
        <T>void get(String url, RequestCallback<T> callback);

        /**
         * get 请求，无需进行接口毁掉
         * @param url
         */
        void get(String url);

        /**
         * post 请求，不需要进行接口回调
         * @param url 请求地址
         * @param parameters 请求参数集合容器
         */
        void post(String url,List<RequestParameter>parameters);
        /**
         * post 请求，请求结果将在callback中回调，一般在对数据进行增删改时都使用post请求
         * @param parameters 请求参数集合
         * @param url 请求接口地址
         * @param callback 回调接口
         * @param <T> 回调接口返回的数据json对应对象
         */
        <T>void post(String url,List<RequestParameter>parameters, RequestCallback<T> callback);

        /**
         * post同步请求
         * @param url 请求地址接口
         * @param parameters 请求参数
         * @return Response
         */
        Response postSync(String url,List<RequestParameter>parameters);

        /**
         * get 同步请求
         * @param url 请求地址
         * @return Response
         */
        Response getSync(String url);

}
