package mhw.ledou.com.mcd.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.ListUtils;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.adapter.ShopingCartAdapter;
import mhw.ledou.com.mcd.fragment.base.ShopingModel;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class SubmitOrderActivity extends Activity implements OnClickListener {

    public static SubmitOrderActivity mactivity;

    ArrayList<String> array = new ArrayList<String>();
    List<ShopingModel> arraylist = new ArrayList<ShopingModel>();
    ListView sublistview;
    ShopingCartAdapter adapter;
    TextView shu, qian, subzongjia, subtijiao, xinzeng, youhuijuan;
    String zongjia;
    ProgressDialog pd;
    View view;
    ImageView backimg, img, morenimg;

    int page, per_page = 100;
    String price = "";

    String coupon_id = "";

    TostUtlis tostUtlis;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        mactivity = this;
        tostUtlis = TostUtlis.getTost(this);
        array = getIntent().getStringArrayListExtra("array");
        arraylist = (List<ShopingModel>) getIntent().getSerializableExtra("list");
        zongjia = getIntent().getStringExtra("qian");

        shu = (TextView) this.findViewById(R.id.shu);
        qian = (TextView) this.findViewById(R.id.qian);
        subzongjia = (TextView) this.findViewById(R.id.subzongjia);
        subtijiao = (TextView) this.findViewById(R.id.subtijiao);
        xinzeng = (TextView) this.findViewById(R.id.xinzeng);
        youhuijuan = (TextView) this.findViewById(R.id.youhuijuan);
        backimg = (ImageView) this.findViewById(R.id.backimg);
        img = (ImageView) this.findViewById(R.id.img);


        subtijiao.setOnClickListener(this);
        xinzeng.setOnClickListener(this);
        backimg.setOnClickListener(this);
        youhuijuan.setOnClickListener(this);
        sublistview = (ListView) this.findViewById(R.id.sublistview);

        shu.setText("一共 " + arraylist.size() + " 件商品");
        qian.setText("¥ " + zongjia + "元");
        subzongjia.setText("应付金额：¥ " + getIntent().getStringExtra("qian") + "元");

        adapter = new ShopingCartAdapter(mactivity, arraylist, "sub");

        sublistview.setAdapter(adapter);

        ListUtils.setListViewHeightBasedOnChildren(sublistview);

        getview();
        getdizhi();
    }

    TextView name, phone, itemaddress;
    View dialogview;

    @SuppressWarnings("static-access")
    public void getview() {
        view = this.findViewById(R.id.include2);
        view.setOnClickListener(mactivity);
        name = (TextView) view.findViewById(R.id.name);
        phone = (TextView) view.findViewById(R.id.phone);
        itemaddress = (TextView) view.findViewById(R.id.itemaddress);
        morenimg = (ImageView) view.findViewById(R.id.morenimg);


    }


    public void getdizhi() {
        OkHttpUtils.get()
                .url(HttpUrl.Host + "user?act=address&access_token=" + HomeActivity.activity.user.getString("token", ""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//							"info":{
//								"aid":9,
//										"true_name":"2314",
//										"province_id":2,
//										"city_id":40,
//										"county_id":58,
//										"area_info":"天津 天津市 南开区",
//										"address":"12313",
//										"mob_phone":"13956945835",
//										"tel_phone":null
//							},
                            if (jsonObject.getString("error").equals("0")) {
                                view.setVisibility(View.VISIBLE);
                                xinzeng.setVisibility(View.GONE);
                                JSONObject info = jsonObject.getJSONObject("info");
                                name.setText(info.getString("true_name"));
                                phone.setText(info.getString("mob_phone"));
                                itemaddress.setText(info.getString("area_info")+info.getString("address"));
                            } else {
                                tostUtlis.setString(jsonObject.getString("msg"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub


        super.onResume();
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.subtijiao:
                setdata();
                //提交订单
//			intent.setClass(mactivity, PlaceOrderActivity.class);
//			intent.putStringArrayListExtra("array", array);
//			intent.putExtra("index", "1");
//			intent.putExtra("coupon_id", coupon_id);
//			startActivity(intent);
//			finish();
                break;
            case R.id.xinzeng:
                intent.setClass(mactivity, SelectAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.include2:
                intent.setClass(mactivity, SelectAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.backimg:
                finish();
                break;
            case R.id.youhuijuan:

                break;

        }
    }

    String cid="";
    void setdata() {
        for (int i = 0; i <array.size() ; i++) {
            if (i == array.size()-1){
                cid = cid+array.get(i);
            }else{
                cid = cid+array.get(i)+",";
            }
        }
        Log.i("cid",cid+"");
        Log.i("cid",HttpUrl.Host+"cart?act=submit&cid=" + cid + "&access_token=" + HomeActivity.activity.user.getString("token", ""));
        OkHttpUtils.get()
                .url(HttpUrl.Host+"cart?act=submit&cid=" + cid + "&access_token=" + HomeActivity.activity.user.getString("token", ""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            if (jsonObject.getString("error").equals("0")){
                                JSONObject info = jsonObject.getJSONObject("info");
                                String oid = info.getString("oid");
                                setdata2(oid);
                            }

                            tostUtlis.setString(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }




    void  setdata2(String oid){

        OkHttpUtils.get()
                .url("http://caigou.mcdsh.com/app/order?act=submit&oid="+oid+"&access_token="+HomeActivity.activity.user.getString("token",""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            JSONObject j = new JSONObject(response);
                            tostUtlis.setString(j.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    };



}
