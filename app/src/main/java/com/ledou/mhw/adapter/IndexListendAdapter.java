package com.ledou.mhw.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ledou.mhw.R;
import com.zhy.autolayout.utils.AutoUtils;

public class IndexListendAdapter extends BaseAdapter{
     List<String> list;
     LayoutInflater inflater;
     Context context;
     int index = 0;
	public IndexListendAdapter(List<String> list,Context context,LayoutInflater inflater) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	
    public void setSeclection(int position) {
             index = position;
       }
	
	

public class viewhold{
	TextView  tvtitle;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	return list.size();
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return list.get(position);
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@SuppressLint("ViewHolder")
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	    convertView = inflater.inflate(R.layout.end, null);
		TextView tv = (TextView) convertView.findViewById(R.id.leimutext);
		tv.setText(list.get(position));
	    AutoUtils.autoSize(convertView);
	return convertView;
}


}
