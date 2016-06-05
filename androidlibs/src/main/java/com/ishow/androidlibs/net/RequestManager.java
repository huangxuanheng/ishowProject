package com.ishow.androidlibs.net;

import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxuanheng on 16-5-25.
 * 网络管理者，每一个需要访问网络的页面都需要有该对象，一般在请求多个网络请求，
 * <br/>而用户却出发了其他事件而跳转到其他页面时取消这些网络请求
 * <br/>该类的对象，必须通过工厂来生产，不能new对象
 */
public class RequestManager {

    private List<RequestCall>requestCalls;


    private RequestManager() {
        requestCalls=new ArrayList<>();
    }

    /**
     * 注册网络请求
     * @param requestCall 请求对象
     */
    public void registerRequest(RequestCall requestCall){
        requestCalls.add(requestCall);
    }

    /**
     * 取消当前页面所有的未请求完成的网络请求
     */
    public void cancleRequest(){
        for(RequestCall requestCall:requestCalls){
            requestCall.cancel();
        }
    }

    /**
     * 清除集合中所有的网络请求对象
     */
    public void clearRequest(){
        requestCalls.clear();
    }

    /**
     * 移除指定的网络请求对象
     * @param requestCall 网络请求对象
     */
    public void removeRequest(RequestCall requestCall){
        requestCalls.remove(requestCall);
    }
}
