package com.ledou.mhw;

import java.lang.reflect.Field;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class ListUtils {
    /**
     * ���¼���ListView�ĸ߶�
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = 50 + totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * @param listView
     * @param group
     */
    public static void setExpandableListViewChildHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    /**
     * ���¼���ExpandableListView�ĸ߶�
     *
     * @param listView
     */
    public static void setExpandableListViewHeightBasedOnChildren(ListView listView) {
        // ��ȡListView��Ӧ��Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()�������������Ŀ
            View listItem = listAdapter.getView(i, null, listView);
            // ��������View �Ŀ��
            listItem.measure(0, 0);
            // ͳ������������ܸ߶�
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�
        // params.height���õ�����ListView������ʾ��Ҫ�ĸ߶�
        listView.setLayoutParams(params);
    }

    

    public static void setListViewHeightBasedOnChildren(GridView listView) {  
        // ��ȡlistview��adapter  
           ListAdapter listAdapter = listView.getAdapter();  
           if (listAdapter == null) {  
               return;  
           }  
           // �̶��п��ж�����  �˴���һ�ж����item��Ҫ����һ�ؼ��е�һ��
           int col = 4;// listView.getNumColumns();  
           int totalHeight = 0;  
           // iÿ�μ�4���൱��listAdapter.getCount()С�ڵ���4ʱ ѭ��һ�Σ�����һ��item�ĸ߶ȣ�  
           // listAdapter.getCount()С�ڵ���8ʱ�������θ߶����  
           for (int i = 0; i < listAdapter.getCount(); i += col) {  
            // ��ȡlistview��ÿһ��item  
               View listItem = listAdapter.getView(i, null, listView);  
               listItem.measure(0, 0);  
               // ��ȡitem�ĸ߶Ⱥ�  
               totalHeight += listItem.getMeasuredHeight();  
           }  
      
           // ��ȡlistview�Ĳ��ֲ���  
           ViewGroup.LayoutParams params = listView.getLayoutParams();  
           // ���ø߶�  
           params.height = totalHeight;  
           // ����margin  
           ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);  
           // ���ò���  
           listView.setLayoutParams(params);  
       }  
    
    
    
    
    public static void setListViewHeightBasedOnChildren2(GridView listView) {  
        // ��ȡlistview��adapter  
           ListAdapter listAdapter = listView.getAdapter();  
           if (listAdapter == null) {  
               return;  
           }  
           // �̶��п��ж�����  �˴���һ�ж����item��Ҫ����һ�ؼ��е�һ��
           int col = 6;// listView.getNumColumns();  
           int totalHeight = 0;  
           // iÿ�μ�4���൱��listAdapter.getCount()С�ڵ���4ʱ ѭ��һ�Σ�����һ��item�ĸ߶ȣ�  
           // listAdapter.getCount()С�ڵ���8ʱ�������θ߶����  
           for (int i = 0; i < listAdapter.getCount(); i += col) {  
            // ��ȡlistview��ÿһ��item  
               View listItem = listAdapter.getView(i, null, listView);  
               listItem.measure(0, 0);  
               // ��ȡitem�ĸ߶Ⱥ�  
               totalHeight += listItem.getMeasuredHeight();  
           }  
      
           // ��ȡlistview�Ĳ��ֲ���  
           ViewGroup.LayoutParams params = listView.getLayoutParams();  
           // ���ø߶�  
           params.height = totalHeight;  
           // ����margin  
           ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);  
           // ���ò���  
           listView.setLayoutParams(params);  
       }  
    
    
    
    
  //gridview�߶ȼ���
  	public static void setGridViewHeightBasedOnChildren2(GridView gridView) {
  		// ��ȡGridView��Ӧ��Adapter
           ListAdapter listAdapter = gridView.getAdapter();
  		if (listAdapter == null){
  		return;
  		}
  		int rows;
  		int columns = 0;
  	int horizontalBorderHeight = 0;
  	Class<?> clazz = gridView.getClass();
  		try {
  		Field column = clazz.getDeclaredField("mRequestedNumColumns");
  		column.setAccessible(true);
  		columns = (Integer) column.get(gridView);
  		// ���÷��䣬ȡ�ú���ָ��߸߶�
  		Field horizontalSpacing = clazz.getDeclaredField("mRequestedHorizontalSpacing");
  		horizontalSpacing.setAccessible(true);
  		horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
  		} catch (Exception e) {
  // TODO: handle exception
  e.printStackTrace();
  		}
  if (listAdapter.getCount() % columns > 0) {
  		rows = listAdapter.getCount() / columns + 1;
  		} else {
  		rows = listAdapter.getCount() / columns;
  		}int totalHeight = 0;
  		for (int i = 0; i < rows; i++) { // ֻ����ÿ��߶�*����
  		View listItem = listAdapter.getView(i, null, gridView);
  		listItem.measure(0, 0); // ��������View �Ŀ��
  		totalHeight += listItem.getMeasuredHeight(); // ͳ������������ܸ߶�
  		}
           ViewGroup.LayoutParams params = gridView.getLayoutParams();
  		params.height = totalHeight + horizontalBorderHeight * (rows - 1)+10;// �����Ϸָ����ܸ߶�
  		gridView.setLayoutParams(params);
  	}
    
    
    
    
	public static void setWH(Context context,View view,int w,int h){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int width = dm.widthPixels;
//		int height = dm.heightPixels;
		LayoutParams params= (LayoutParams) view.getLayoutParams();
//		��ȡ��ǰ�ؼ��Ĳ��ֶ���
//	        ((MarginLayoutParams) params).setMargins(
//					(int) context.getResources().getDimension(R.dimen.s10), 
//					(int) context.getResources().getDimension(R.dimen.s10), 
//					(int) context.getResources().getDimension(R.dimen.s10), 
//					(int) context.getResources().getDimension(R.dimen.s10));
	        params.width = LayoutParams.MATCH_PARENT;
	        params.height = width/w*h;//���õ�ǰ�ؼ����ֵĸ߶�
		view.setLayoutParams(params);//�����úõĲ��ֲ���Ӧ�õ��ؼ���
		
	}
    
    
    
	
	
	
	
	//�����Ǻ�ɫ����ķ�������

	public static Uri getFileUri(Uri uri,Context context){
	    if (uri.getScheme().equals("file")) {
	        String path = uri.getEncodedPath();
	        Log.d("tag", "path1 is " + path);
	        if (path != null) {
	            path = Uri.decode(path);
	            Log.d("tag", "path2 is " + path);
	            ContentResolver cr = context.getContentResolver();
	            StringBuffer buff = new StringBuffer();
	            buff.append("(")
	                    .append(MediaStore.Images.ImageColumns.DATA)
	                    .append("=")
	                    .append("'" + path + "'")
	                    .append(")");
	            Cursor cur = cr.query(
	                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                    new String[] { MediaStore.Images.ImageColumns._ID },
	                    buff.toString(), null, null);
	            int index = 0;
	            for (cur.moveToFirst(); !cur.isAfterLast(); cur
	                    .moveToNext()) {
	                index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
	                // set _id value
	                index = cur.getInt(index);
	            }
	            if (index == 0) {
	                //do nothing
	            } else {
	                Uri uri_temp = Uri
	                        .parse("content://media/external/images/media/"
	                                + index);
	                Log.d("tag", "uri_temp is " + uri_temp);
	                if (uri_temp != null) {
	                    uri = uri_temp;
	                }
	            }
	        }
	    }
	    return uri;
	}
	
	
	
	
	
	
	
	
    
    
    
    
}