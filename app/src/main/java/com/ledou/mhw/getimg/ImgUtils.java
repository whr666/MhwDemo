package com.ledou.mhw.getimg;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ledou.mhw.R;

/**
 * Created by XIAOXIN on 2017/7/7.
 */

public class ImgUtils {
    private static volatile   RequestOptions options;
    private static volatile   ImgUtils imgUtils;
//    private static volatile   Context context;
    private ImgUtils() {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placenumber)//等待图片
                .error(R.drawable.placenumber)//错误图片
                .priority(Priority.HIGH)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);//缓存策略
    }
    public static ImgUtils getimgutils(){
        if (imgUtils==null){
            synchronized (ImgUtils.class){
                if (imgUtils==null){
                    imgUtils = new ImgUtils();
                }
            }
        }
        return imgUtils;
    }

    public  void GetImg(Context context, String url, ImageView imageView){
//        if (context == null || url.equals("") || url.equals(null) || TextUtils.isEmpty(url)){
//            Log.i("context","data null");
//        }else{
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
//        }
    }

//    public  void GetGif(Context context, String url, ImageView imageView){
//        Glide.with(context)
//                .asGif().load(url)
//                .apply(options)
//                .into(imageView);
//    }





}
