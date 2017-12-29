package mhw.ledou.com.mcd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.adapter.ClassfiyAadapter;
import mhw.ledou.com.mcd.fragment.adapter.ShopingCartAdapter;
import mhw.ledou.com.mcd.fragment.base.ClassL;
import mhw.ledou.com.mcd.fragment.base.ShopingModel;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

/**
 * Created by XIAOXIN on 2017/10/17.
 */
public class ShopcartView extends android.app.Fragment {

    public static ShopcartView mview;
    View view;
    TostUtlis tostUtlis;
    List<ShopingModel> list = new ArrayList<ShopingModel>();
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    ShopingCartAdapter adapter;
    public TextView tvCountMoney, jiesuan, shopingtext1;
    public CheckBox ivSelectAll;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.shopcartview, container, false);
        mview = this;
        this.inflater = inflater;
        tostUtlis = TostUtlis.getTost(getActivity());
        intiview();
        getdata();
        return view;
    }

    private void intiview() {
        listView = (ListView) view.findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        ivSelectAll = (CheckBox) view.findViewById(R.id.shopall);//全选
        tvCountMoney = (TextView) view.findViewById(R.id.shopzongjia);//总价
        jiesuan = (TextView) view.findViewById(R.id.jiesuan);//结算
        shopingtext1 = (TextView) view.findViewById(R.id.infobackimg);//编辑

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    if (list == null) {
                        swipeRefreshLayout.setRefreshing(false);
                    } else {
                        getdata();
                    }
                    ivSelectAll.setChecked(false);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥0");
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }


    public void getdata() {

        OkHttpUtils.get()
                .url(HttpUrl.Host + HttpUrl.shopcar + HomeActivity.activity.user.getString("token", ""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            String error = jsonobject.getString("error");
                            if (error.equals("0")) {
                                JSONArray array = jsonobject.getJSONArray("list");
                                list = JSON.parseArray(array.toString(), ShopingModel.class);
                                Log.i("list", list.toString() + "");
                                adapter = new ShopingCartAdapter(getActivity(), list, "shopcar");
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {
                                tostUtlis.setString(jsonobject.getString("msg"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }


}
