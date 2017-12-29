package mhw.ledou.com.mcd.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.getimg.ImgUtils;


/**
 * ImageView创建工厂
 */
public class ViewFactory {
	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(R.layout.view_banner, null);
		ImgUtils.getimgutils().GetImg(context,url,imageView);
		return imageView;
	}
}
