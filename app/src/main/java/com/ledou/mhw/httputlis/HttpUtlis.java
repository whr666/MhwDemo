package com.ledou.mhw.httputlis;

import com.ledou.mhw.jsonutlis.TPostJsonBuilder;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * Created by XIAOXIN on 2017/9/21.
 */

public class HttpUtlis  extends OkHttpUtils{

    public HttpUtlis(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    public static TPostJsonBuilder setjson() {
        return  new TPostJsonBuilder();
    }
}
