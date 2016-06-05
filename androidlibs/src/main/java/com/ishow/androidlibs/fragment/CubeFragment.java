package com.ishow.androidlibs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ishow.androidlibs.activity.BaseActivity;
import com.ishow.androidlibs.api.ICubeFragment;

/**
 * Created by huangxuanheng on 2016/1/5.
 */
public abstract  class CubeFragment extends Fragment implements ICubeFragment {
    protected Context mContext;

    protected Object dataIn;
    protected Object dataOut;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return onCreateView(inflater,savedInstanceState);
    }

    /**
     * 初始化布局，子类必须实现该方法以初始化自己的布局
     * @param inflater
     * @return
     */
    public abstract View onCreateView(LayoutInflater inflater,Bundle savedInstanceState);
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
        this.dataOut=object;
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
