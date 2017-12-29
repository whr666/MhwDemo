package com.ledou.mhw.httputlis;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ledou.mhw.fragment.base.Aclass;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by XIAOXIN on 2017/9/20.
 * 暂时废弃
 */

public class HttpClient {
    private volatile static HttpClient httpClient;
    public volatile static OkHttpClient okHttpClient;
    public HttpClient() {
                okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }
    public static HttpClient getInstance(){
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                }
            }
        }
       return httpClient;

    }



}
