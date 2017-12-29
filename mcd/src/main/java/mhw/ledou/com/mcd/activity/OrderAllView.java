package mhw.ledou.com.mcd.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.adapter.OrderAdapter;
import mhw.ledou.com.mcd.activity.base.NewOrderModel;
import mhw.ledou.com.mcd.activity.base.OrderModel;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class OrderAllView extends AppCompatActivity {

	List<OrderModel> list = new ArrayList<OrderModel>();
	List<NewOrderModel> newlist = new ArrayList<NewOrderModel>();

//	RecyclerView mRecyclerView;
	
	String order_status="";
	int page, perpage=1000;
	
	 private static final String TAG = "MainActivity_log";
	    private RecyclerView mRecyclerView;
	    View view,include,include2;

	TostUtlis tostUtlis;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allview);
		tostUtlis = TostUtlis.getTost(this);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		getdata();
	}

	private void getdata() {
		// TODO Auto-generated method stub
		OkHttpUtils.get()
				.url(HttpUrl.Host+"order?act=list&page=0&per-page=10000&access_token="+ HomeActivity.activity.user.getString("token",""))
				.build()
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {

						tostUtlis.setString(call.request()+"");

					}

					@Override
					public void onResponse(String response, int id) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							if (jsonObject.getString("error").equals("0")){
								JSONArray array = jsonObject.getJSONArray("list");

							}else{

								tostUtlis.setString(jsonObject.getString("msg"));

							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				});



	}
	
	
	
	
	private void intidata() {
		// TODO Auto-generated method stub
      for (int i = 0; i < list.size(); i++) {
      	newlist.add(new NewOrderModel(1,list.get(i).getCreated_at(),"","","","","","",
      			list.get(i).getOrder_status(),list.get(i).getId(),"",list.get(i).getGoods(),list.get(i).getOrder_sn()));
          for (int j = 0; j < list.get(i).getGoods().size(); j++) {
          newlist.add(new NewOrderModel(2, "", list.get(i).getGoods().get(j).getGoods_name(),
          		list.get(i).getGoods().get(j).getGoods_image(),
          		list.get(i).getGoods().get(j).getGoods_specs(),
          		list.get(i).getGoods().get(j).getGoods_price(),
          		list.get(i).getGoods().get(j).getGoods_num(), "","",
          		list.get(i).getId(),list.get(i).getGoods().get(j).getG_id(),list.get(i).getGoods(),list.get(i).getOrder_sn())); 	}
          newlist.add(new NewOrderModel(3,"","","","","","",
        		  list.get(i).getOrder_amount(),list.get(i).getOrder_status(),list.get(i).getId(),"",list.get(i).getGoods(),list.get(i).getOrder_sn()));
      }
		
      OrderAdapter    adapter = new OrderAdapter(newlist, this);
      mRecyclerView.setAdapter(adapter);
      //�������Ľӿڻص�
      adapter.setItemOnClick(new OrderAdapter.itemClickListeren() {
          @Override
          public void headItemClick(int position) {
          }

          @Override
          public void itemClick(int position) {
        	  Intent inetn = new Intent();
        	  inetn.setClass(OrderAllView.this, CommodityInfoActivity.class);
        	  inetn.putExtra("id", newlist.get(position).getGid());
        	  startActivity(inetn);
//        	  getActivity().finish();
          }
      });
      
      
      
      
	}

}
