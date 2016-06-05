package com.ishow.androidlibs.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ishow.androidlibs.factory.ManagerFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huangxuanheng on 16-5-25.
 */
public class RemoService implements IRemoService{

    private static RemoService instance;

    private RequestManager requestManager= ManagerFactory.getRequestManager();
    public static RemoService getInstance(){
        if(instance==null){
            instance=new RemoService();
        }
        return instance;
    }
    @Override
    public <T>void get(String url, final RequestCallback<T> callback) {
        GetBuilder getBuilder = OkHttpUtils
                .get()
                .url(url);
        getBuilder.addHeader("accept-encoding", "gzip");
        final RequestCall build = getBuilder.build();

        build.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                if(callback!=null){
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(String response) {
                if(callback!=null){
                    protocolData(response, callback);
                }
            }



            @Override
            public void onBefore(Request request) {
                if(callback!=null){
                    callback.onStart();
                }
                requestManager.registerRequest(build);
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                if(callback!=null){
                    callback.onEnd();
                }
                requestManager.removeRequest(build);
                super.onAfter();
            }
        });
    }

    @Override
    public void get(String url) {
        get(url,null);
    }

    @Override
    public void post(String url, List<RequestParameter> parameters) {
        post(url,parameters,null);
    }

    private <T> void protocolData(String response,final RequestCallback<T> callback) {
        Gson gson = new Gson();
        ResponseData<T> responseData = gson.fromJson(response, new TypeToken<ResponseData<T>>() {}.getType());

        T t=responseData.getData();
        Type type = callback.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType= (ParameterizedType) type;
            Type genericType = parameterizedType.getActualTypeArguments()[0];
            t=gson.fromJson(gson.toJson(responseData.getData()),genericType);
        }
        callback.onSuccess(t);
    }
    @Override
    public <T>void post(String url,List<RequestParameter> parameters, final RequestCallback<T> callback) {
        PostFormBuilder formBuilder = OkHttpUtils
                .post()
                .url(url).addHeader("accept-encoding","gzip");

        if(parameters!=null&&parameters.size()>0){
            for (RequestParameter parameter:parameters){
                formBuilder.addParams(parameter.getKey(),parameter.getValue());
            }
        }
        final RequestCall build = formBuilder.build();

        build.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                if(callback!=null){
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(String response) {
                if(callback!=null){
                    protocolData(response, callback);
                }
            }



            @Override
            public void onBefore(Request request) {
                if(callback!=null){
                    callback.onStart();
                }
                requestManager.registerRequest(build);
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                if(callback!=null){
                    callback.onEnd();
                }
                requestManager.removeRequest(build);
                super.onAfter();
            }
        });
    }

    @Override
    public Response postSync(String url, List<RequestParameter> parameters) {
        try {
            PostFormBuilder post = OkHttpUtils.post().url(url).addHeader("accept-encoding", "gzip");

            if(parameters!=null&&parameters.size()>0){
                for (RequestParameter parameter:parameters){
                    post.addParams(parameter.getKey(),parameter.getValue());
                }
            }
            Response response = post.build()//
                    .execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response getSync(String url) {
        try {
            GetBuilder getBuilder = OkHttpUtils
                    .get()//
                    .url(url).addHeader("accept-encoding","gzip");
            Response response = getBuilder.build()//
                    .execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
