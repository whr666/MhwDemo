package com.ledou.mhw.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ledou.mhw.HomeActivity;
import com.ledou.mhw.R;
import com.ledou.mhw.activity.NoLoginActivity;
import com.ledou.mhw.algorithm.ShoppingCartBiz;
import com.ledou.mhw.fragment.adapter.ShopcarAdapter;
import com.ledou.mhw.fragment.base.Cart;
import com.ledou.mhw.fragment.base.Cart2;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 *
 */
public class ShopcartView extends Fragment implements View.OnClickListener {
    @BindView(R.id.listview)
    ExpandableListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.infobackimg)
    TextView infobackimg;
    @BindView(R.id.shopzongjia)
    TextView shopzongjia;
    @BindView(R.id.jiesuan)
    TextView jiesuan;
    @BindView(R.id.shopall)
    ImageView isALL;

    TostUtlis tostUtlis;


    List<Cart2> shopcarlist = new ArrayList<>();
    ShopcarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcartview, container, false);
        tostUtlis = new TostUtlis(getActivity());
        NoteView.inject(this, view);
        setonclik();
        getdata();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    getdata();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        return view;
    }

    private void setonclik() {

        jiesuan.setOnClickListener(this);
    }

    /**
     * 展开所有组
     */
    private void expandAllGroup() {
        for (int i = 0; i < shopcarlist.size(); i++) {
            listView.expandGroup(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jiesuan:

                break;


            default:

                break;
        }
    }


    public void getdata() {
        Map map = new HashMap();
        HttpUtlis.setjson()
                .token(HomeActivity.activity.user.getString("token", ""))
                .pub_args(map)
                .method("cart.goodsGet")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("call", call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (TextUtils.isEmpty(array.toString())) {

                            } else {
                                List<Cart> list = JSON.parseArray(array.toString(), Cart.class);
                                for (int i = 0; i < list.size(); i++) {
                                    String store_id = list.get(i).getStore_id();
                                    String store_name = list.get(i).getStore_name();
                                    shopcarlist = list.get(i).getCart_goods();
                                }
                                setAdapter();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }


    private void setAdapter() {
        adapter = new ShopcarAdapter(getActivity(), shopcarlist);
        listView.setAdapter(adapter);
        expandAllGroup();
        adapter.notifyDataSetChanged();
        adapter.setOnShoppingCartChangeListener(new ShopcarAdapter.OnShoppingCartChangeListener() {

            public void onDataChange(String selectCount, String selectMoney) {
                String countMoney = String.format(getResources().getString(R.string.count_money), selectMoney);
                String countGoods = String.format(getResources().getString(R.string.count_goods), selectCount);
                shopzongjia.setText(countMoney);
                jiesuan.setText(countGoods);
            }
            public void onSelectItem(boolean isSelectedAll) {
                ShoppingCartBiz.checkItem(isSelectedAll,isALL);
            }
        });
        //通过监听器关联Activity和Adapter的关系，解耦；
        View.OnClickListener listener = adapter.getAdapterListener();
        if (listener != null) {
            //即使换了一个新的Adapter，也要将“全选事件”传递给adapter处理；
            isALL.setOnClickListener(adapter.getAdapterListener());
            //结算时，一般是需要将数据传给订单界面的
            jiesuan.setOnClickListener(adapter.getAdapterListener());
        }
        listView.setGroupIndicator(null);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        if(hidden){
            //被覆盖时

        }else{
            //出现在上面时
            if (HomeActivity.activity.user.getString("token","").equals("")){
                Intent intent = new Intent(getActivity(),NoLoginActivity.class);
                startActivity(intent);
            }
        }
        super.onHiddenChanged(hidden);
    }
}
