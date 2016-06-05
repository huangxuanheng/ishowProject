package com.ishow.ishow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ishow.androidlibs.net.RemoService;
import com.ishow.androidlibs.net.RequestCallback;
import com.ishow.androidlibs.net.ResponseData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://192.168.0.12:8088/IShowOrgInterface/api/membercard/querymemberCard?memberId=293";

        new Thread(){
            @Override
            public void run() {
                onCreate(null);
            }
        }.start();

        RemoService.getInstance().get(url, new RequestCallback<List<MemberCard>>() {
            @Override
            public void onSuccess(List<MemberCard> memberCards) {
                System.out.println(memberCards.get(0).getMemberId());
            }
        });
    }
}
