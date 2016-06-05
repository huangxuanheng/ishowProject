package com.ishow.androidlibs.net;

/**
 * Created by huangxuanheng on 16-5-25.
 */
public class ResponseData<T> {
    private int code;     //错误类型
    private String message;    //错误信息，成功则为空
    private T data;    //数据实体

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
