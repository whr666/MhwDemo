package mhw.ledou.com.mcd.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import mhw.ledou.com.mcd.R;


/**
 * Created by XIAOXIN on 2017/9/28.
 */

public class ShowDialog {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static Dialog setview(Activity activity,View view){
        final Dialog dialog = new Dialog(activity, R.style.theme_dialog);// 创建自定义样式dialog
            dialog.setCancelable(true);// 可以用“返回键”取消
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(view);
            Display display = activity.getWindowManager().getDefaultDisplay() ;
            display.getWidth();
            display.getHeight();
            Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.AnimBottom);
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                layoutParams.height = display.getHeight()/2;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            //        layoutParams.alpha=0f;
            window.setAttributes(layoutParams);
        dialog.show();
        return dialog;
    }





}
