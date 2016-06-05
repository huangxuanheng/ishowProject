package com.ishow.androidlibs.net;

/**
 * Created by huangxuanheng on 16-5-25.
 */
public abstract class RequestCallback<T> {

    public abstract void onSuccess(T t);

    public void onFailure(Exception e){

    }

    public void onStart(){

    }

    public void onEnd(){

    }
}
