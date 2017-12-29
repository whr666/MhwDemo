package com.ledou.mhw;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.jingewenku.abrahamcaijin.commonutil.AppLogMessageMgr;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;

import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;

/**
 * Created by XIAOXIN on 2017/9/20.
 */

public class Aplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");
//         headers.put("commonHeaderKey2", "commonHeaderValue2");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type","application/json")
                                .addHeader("Accept","application/json;charset=utf-8")
                                .build();
                        return chain.proceed(request);
                    }
                })
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

    }
}