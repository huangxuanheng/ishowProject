package com.ishow.androidlibs.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangxuanheng on 16-5-30.
 */
public class ThreadManager {
    private static ThreadManager instance;
    private static  ExecutorService executorService;
    private ThreadManager(){
        executorService = Executors.newFixedThreadPool(5);
    }

    public static ThreadManager getPoolProxy(){
        if(instance==null){
            instance=new ThreadManager();
        }
        return instance;
    }

    /**
     * 执行线程池
     * @param cammand
     */
    public void execute(Runnable cammand){
        executorService.execute(cammand);
    }
}
