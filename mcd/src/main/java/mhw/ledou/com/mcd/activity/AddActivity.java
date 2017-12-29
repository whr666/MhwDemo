package mhw.ledou.com.mcd.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.alibaba.fastjson.JSON;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.adapter.AddAdapter;
import mhw.ledou.com.mcd.activity.base.ShengModel;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.json.JsonUtils;

public class AddActivity extends Activity {
	
	public static	AddActivity mativity;
	
ListView addlistview;
List<ShengModel> shenglist = new ArrayList<ShengModel>();
AddAdapter adapter;
int index=0;


List<ShengModel> shilist;
List<ShengModel> qulist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		mativity= this;
		
		addlistview = (ListView) this.findViewById(R.id.addlistview);		
		
		
		getsheng();
		
		
		
		addlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(index==0){
					HttpUrl.shengid  = shenglist.get(position).getArea_id();
					HttpUrl.sheng = shenglist.get(position).getArea_name();
                    JSONArray array = JsonUtils.toarray(mativity, "shi.json");
                    shilist = JSON.parseArray(array.toString(), ShengModel.class);
					shi(HttpUrl.shengid,shilist);
					index =1;
				}else if(index==1){
					HttpUrl.shiid = shilist.get(position).getArea_id();
					HttpUrl.shi = shilist.get(position).getArea_name();
					JSONArray array = JsonUtils.toarray(mativity, "qu.json");
					qulist = JSON.parseArray(array.toString(), ShengModel.class);
					qu(HttpUrl.shiid,qulist);
					index=2;
				}else if(index==2){
					HttpUrl.quid = qulist.get(position).getArea_id();
					HttpUrl.qu = qulist.get(position).getArea_name();
					finish();
				}
			}
		});
		
		
	}
	
	private void qu(String shiid,List<ShengModel> list) {
		// TODO Auto-generated method stub
		 qulist = JsonUtils.getlist(list, shiid);
		adapter = new AddAdapter(qulist, getLayoutInflater(), mativity);
		addlistview.setAdapter(adapter);
	}
	private void shi(String shengid,List<ShengModel> list) {
		// TODO Auto-generated method stub
		 shilist = JsonUtils.getlist(list, shengid);
		adapter = new AddAdapter(shilist, getLayoutInflater(), mativity);
		addlistview.setAdapter(adapter);
	}
	private void getsheng() {
		// TODO Auto-generated method stub
		JSONArray array = JsonUtils.toarray(mativity, "sheng.json");
		shenglist = JSON.parseArray(array.toString(), ShengModel.class);
		adapter = new AddAdapter(shenglist, getLayoutInflater(), mativity);
		addlistview.setAdapter(adapter);
	}
	
	
	
	
}
