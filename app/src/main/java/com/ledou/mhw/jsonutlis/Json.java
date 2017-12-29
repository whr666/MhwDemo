package com.ledou.mhw.jsonutlis;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingewenku.abrahamcaijin.commonutil.JsonUtils;
import com.jingewenku.abrahamcaijin.commonutil.encryption.MD5Utils;
import com.ledou.mhw.fragment.base.Aclass;
import com.ledou.mhw.httputlis.HttpUrl;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by XIAOXIN on 2017/9/20.
 */

public class Json {
    private static Json json;
    private static Gson gson;
    private Json() {
        gson = new Gson();
    }
    public  static Json getJson(){
        if(json==null){
            json = new Json();
        }
        return json;
    }
    /**gson
     * 返回List<class>数组
     * 有问题，回头再写
     */
    public  <T> List<T> stringToList(String string,Class<T> cla){
        List<T> list = new ArrayList<>();
        if (string.isEmpty()){
            Log.i("gson","this null");
        }else{
            Type type = new TypeToken<List<T>>(){}.getType();
            list = gson.fromJson(string, type);
        }
     return list;
    };











}
