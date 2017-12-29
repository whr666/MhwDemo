package mhw.ledou.com.mcd.activity.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.base.ShengModel;

public class AddAdapter extends BaseAdapter{
List<ShengModel> list;
LayoutInflater inflater;
Context context;


	public AddAdapter(List<ShengModel> list, LayoutInflater inflater, Context context) {
	super();
	this.list = list;
	this.inflater = inflater;
	this.context = context;
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

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ShengModel sm= list.get(position);
		HoldView hold; 
		if(view==null){
			hold = new HoldView();
			view = inflater.inflate(R.layout.additem, null);
			hold.shi = (TextView) view.findViewById(R.id.shi);
			view.setTag(hold);
		}else{
			hold = (HoldView) view.getTag();
		}
		
		hold.shi.setText(sm.getArea_name());
		
		return view;
	}

	public class HoldView{
		TextView shi;
	}
	
	
}
