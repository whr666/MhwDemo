package mhw.ledou.com.mcd.activity.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.base.AddressModel;

public class AddressAdapter2 extends BaseAdapter{
	List<AddressModel> list;
	LayoutInflater infalter;
	Context  context;
int index;
	
	public AddressAdapter2(List<AddressModel> list, LayoutInflater infalter, Context context) {
		super();
		this.list = list;
		this.infalter = infalter;
		this.context = context;
	}

	public void setisele(int position){
		index = position;
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
		HoldView hold;
		AddressModel am = list.get(position);
		if(view==null){
			hold = new  HoldView();
			view = infalter.inflate(R.layout.addressitem2, null);
			hold.name = (TextView) view.findViewById(R.id.name);
			hold.phone = (TextView) view.findViewById(R.id.phone);
			hold.itemaddress = (TextView) view.findViewById(R.id.itemaddress);
			hold.img = (ImageView) view.findViewById(R.id.morenimg);
	        view.setTag(hold);
		}else{
			hold = (HoldView) view.getTag();
		}
		if(am.getDefaults().equals("0")){
			hold.img.setVisibility(View.INVISIBLE);
		}else if(am.getDefaults().equals("1")){
			hold.img.setVisibility(View.VISIBLE);
		}
		
//		
		hold.name.setText(am.getDa_name());
		hold.phone.setText(am.getDa_phone());
		hold.itemaddress.setText(am.getDa_area()+am.getDa_address());
		
		
		if(index == position){
			hold.img.setVisibility(View.VISIBLE);
		}else{
			hold.img.setVisibility(View.INVISIBLE);
		}
		
		
		
		return view;
	}

	
	
	public class HoldView{
		TextView name;
		TextView phone;
		TextView itemaddress;
		ImageView img;
	}
	
	
}
