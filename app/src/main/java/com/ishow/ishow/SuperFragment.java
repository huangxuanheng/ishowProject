package com.ishow.ishow;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangxuanheng on 2016/1/5.
 */
public abstract  class SuperFragment<T> extends Fragment implements IFragmentLifeCycle {
    protected Context mContext;

    protected Object dataIn;    //进入页面时传进来的数据，2.0.1版本后引入
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化布局，子类必须实现该方法以初始化自己的布局
     * @param inflater
     * @return
     */
    public abstract View initView(LayoutInflater inflater,Bundle savedInstanceState);
    /**
     * 初始化数据，子类需要实现该方法以初始化自己的数据
     */
    public void initData(){

    }

    /**
     * 关闭当前activity
     */
    protected void finish(){
        ((BaseActivity)mContext).finish();
    }

    @Override
    public void onLeave() {

    }

    @Override
    public void onBack() {

    }

    @Override
    public void onBackWithData(Object object) {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void onEnter(Object data) {
        this.dataIn=data;
    }

    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }
}
