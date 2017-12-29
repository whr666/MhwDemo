package mhw.ledou.com.mcd.getimg;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import mhw.ledou.com.mcd.R;

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
                .placeholder(R.mipmap.placenumber)//等待图片
                .error(R.mipmap.placenumber)//错误图片
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

    public  void GetImg(Context context, Object url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

//    public  void GetGif(Context context, String url, ImageView imageView){
//        Glide.with(context)
//                .asGif().load(url)
//                .apply(options)
//                .into(imageView);
//    }





}
