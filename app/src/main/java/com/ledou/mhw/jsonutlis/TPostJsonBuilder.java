package com.ledou.mhw.jsonutlis;

import android.util.Log;

import com.jingewenku.abrahamcaijin.commonutil.JsonUtils;
import com.jingewenku.abrahamcaijin.commonutil.encryption.MD5Utils;
import com.ledou.mhw.httputlis.HttpUrl;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.request.PostStringRequest;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * pub_args 公共参数
 * method 接口类型
 * token token
 */
public class TPostJsonBuilder extends OkHttpRequestBuilder<TPostJsonBuilder> {

private String paramContent;
private MediaType mediaType;

private Map<String, Object> pub_args;
private String method;
private String token;


public TPostJsonBuilder() {
    this.pub_args = new HashMap<>() ;
}

public TPostJsonBuilder(String paramContent,MediaType mediaType, Map<String, Object> pub_args,String method,String token) {
    this.paramContent = paramContent;
    this.mediaType = mediaType;
    this.pub_args = pub_args;
    this.method = method;
    this.token = token;
}

public TPostJsonBuilder pub_args(Map<String, Object> args) {
    this.pub_args = args  ;
    return  this;
}

    public TPostJsonBuilder method(String method) {
        this.method = method  ;
        return  this;
    }

    public TPostJsonBuilder token(String token) {
        this.token = token  ;
        return  this;
    }

public TPostJsonBuilder paramContent(String content) {
    if (content != null) {
        this.paramContent = content;
    } else if (this.pub_args.size() != 0){
        this.paramContent = initParams(token,this.pub_args,method) ;
    } else {
        Log.w("args cannot be null !","args cannot be null !");
    }
    return this;
}
//这个方法就是拼接参数的关键了，将传入的公参和私参拼接成json string
public String initParams(String token,Map<String, Object> pub_args,String method) {

    long timestamp = System.currentTimeMillis()/1000;
    Map<String,Object> pri_args = new HashMap<>();
    pri_args.put("access_token",token);
    pri_args.put("key", MD5Utils.encryptMD5(HttpUrl.Salt+ timestamp));
    pri_args.put("timestamp",timestamp);

    Map<String,Object> args = new HashMap<>() ;
    args.put("method",method);
    args.put("auth",pri_args);
    args.put("body",pub_args);
    return JsonUtils.toJson(args);
}


public TPostJsonBuilder mediaType(MediaType mediaType)
{
    this.mediaType = mediaType;
    return this;
}
@Override
public RequestCall build() {
    paramContent = initParams(token,this.pub_args,method);
    mediaType = MediaType.parse("application/json;charset=utf-8");
//    return new PostStringRequest(url,tag,params,headers,paramContent,mediaType,id).build();
    return new PostStringRequest(HttpUrl.Host,tag,params,headers,paramContent,mediaType,id).build();
}
}