package com.ledou.mhw.jsonutlis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by XIAOXIN on 2017/9/26.
 */

public class InfoData {


    public  static JSONObject getjsonobj(String string){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public static JSONArray getjsonarray(String string){
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public  static Object ObjectgetString(){



     return null;
    }



}
