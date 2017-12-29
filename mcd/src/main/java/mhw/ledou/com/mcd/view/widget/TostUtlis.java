package mhw.ledou.com.mcd.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mhw.ledou.com.mcd.R;

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



    /**
     * showloding
     * @param context
     * @param msg
     * @return
     */
    public static Dialog loding(Context context, String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息
        final Dialog mProgressDialog = new Dialog(context,R.style.theme_dialog);
        final int MSG_DISMISS_DIALOG = 0;
        Handler mHandler = new Handler(){
            @SuppressWarnings("unused")
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                if(MSG_DISMISS_DIALOG == msg.what){
                    if(null != mProgressDialog){
                        if(mProgressDialog.isShowing()){
                            mProgressDialog.dismiss();
                        }
                    }
                }
            }
        };
        mProgressDialog.setCancelable(false);// 不可以用“返回键”取消
        mProgressDialog.setContentView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        mProgressDialog.show();
        mHandler.sendEmptyMessageDelayed(MSG_DISMISS_DIALOG, 3000);

        return mProgressDialog;
    }








}
