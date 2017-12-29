package com.ledou.mhw.getimg;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Getimg {

   @SuppressWarnings("static-access")
public static void getimgurl(Context context, String url, ImageView img){
	   if(url.equals("")){
		   Glide
		   .with(context)
		   .load(url)//��ַ
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.shoplist)//�����е�ͼ
//		  .error(R.drawable.icon_error)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }else{
		   
	
	   String imgurl = url.substring(4, 5);
	   if(imgurl.equals("s")){
		   StringBuffer sb = new StringBuffer(url);
		   sb.deleteCharAt(4);
		   sb.insert(4,"");
		   Glide
		   .with(context)
		   .load(sb.toString())//��ַ
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.shoplist)//�����е�ͼ
//		  .error(R.drawable.icon_error)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }else{
		   Glide
		   .with(context)
		   .load(url)//��ַ
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.shoplist)//�����е�ͼ
//		  .error(R.drawable.icon_error)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }
	  
	   }
	   
	   
   } 
   
   @SuppressWarnings("static-access")
public static void getyuanimgurl(Context context, String url, ImageView img){
	   
	   if(url.equals("")){
		   Glide
		   .with(context)
		   .load(url)//��ַ
//		   .transform(new GlideCircleTransform(context))
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.head)//�����е�ͼ
//		  .error(R.drawable.head)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }else{
	   
	   String imgurl = url.substring(4, 5);
	   if(imgurl.equals("s")){
		   StringBuffer sb = new StringBuffer(url);
		   sb.deleteCharAt(4);
		   sb.insert(4,"");
		   Glide.with(context)
		   .load(sb.toString())//��ַ
//		   .transform(new GlideCircleTransform(context))
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.shoplist)//�����е�ͼ
//		  .error(R.drawable.head)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }else{
		   Glide
		   .with(context)
		   .load(url)//��ַ
//		   .transform(new GlideCircleTransform(context))
//		   .dontAnimate().fitCenter()//ͼƬ��������
//		   .skipMemoryCache(true)//ͼƬ����
//		   .diskCacheStrategy(DiskCacheStrategy.ALL)//source����ԭʼresult��������ͼ�ĸ�״̬all����ȫ��none��������
//		  .placeholder(R.drawable.shoplist)//�����е�ͼ
//		  .error(R.drawable.icon_error)//����ʧ��ͼ
//		  .override(width, height)//ͼƬ��С
		   .into(img);
	   }
	   
	   
	   }
	   
	   
	   
	   
   } 
	
   
   
   
   
   
   
   
   
   
}
