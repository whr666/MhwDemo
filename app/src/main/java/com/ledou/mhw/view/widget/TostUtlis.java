package com.ledou.mhw.view.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by XIAOXIN on 2017/9/25.
 */

public class TostUtlis {
    public static TostUtlis tostUtlis;
    Context context;
    public TostUtlis(Context context) {
        this.context = context;
    }
    public static TostUtlis getTost(Context context){
        if(tostUtlis==null){
            tostUtlis = new TostUtlis(context);
        }
        return tostUtlis;
    }



    public void  setString(String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
