package mhw.ledou.com.mcd.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.adapter.AddressAdapter2;
import mhw.ledou.com.mcd.activity.base.AddressModel;

public class SelectAddressActivity extends Activity implements OnClickListener{
	public static SelectAddressActivity mactivity;
	TextView guanli;
	ListView selectaddressistview;
	Dialog pd;
	List<AddressModel> list = new ArrayList<AddressModel>();
	AddressAdapter2 adapter;
	ImageView backimg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_address);
		mactivity=this;
		guanli = (TextView) this.findViewById(R.id.guanli);
		backimg = (ImageView) this.findViewById(R.id.backimg);
		selectaddressistview = (ListView) this.findViewById(R.id.selectaddressistview);
		guanli.setOnClickListener(this);
		backimg.setOnClickListener(this);
		
		

		
		selectaddressistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				setmoren(list.get(position-1).getId());
				adapter.setisele(position-1);
				adapter.notifyDataSetChanged();
				
			}
		});
		
		
		
		
	}

	
	public void setmoren(String id){
		
	}
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		getlist();
		
		super.onResume();
	}

	private void getlist() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.guanli:
			intent.setClass(mactivity, AddressEditActivity.class);
			startActivity(intent);
			break;
		case R.id.backimg:
			finish();
			break;
		default:
			break;
		}
	}
}
