package com.ishow.androidlibs.factory;

import com.ishow.androidlibs.net.RequestManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by huangxuanheng on 16-5-25.
 */
public class ManagerFactory {

    /**
     * 获取网络请求管理者对象
     * @return
     */
    public static RequestManager getRequestManager(){
        Class<RequestManager>clzz=RequestManager.class;
        try {
            Constructor<RequestManager> requestManagerConstructor = clzz.getDeclaredConstructor();
            requestManagerConstructor.setAccessible(true);
            return requestManagerConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
