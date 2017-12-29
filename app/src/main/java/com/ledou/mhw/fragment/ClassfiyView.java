package com.ledou.mhw.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jingewenku.abrahamcaijin.commonutil.JsonUtils;
import com.ledou.mhw.R;
import com.ledou.mhw.adapter.IndexListendAdapter;
import com.ledou.mhw.fragment.adapter.ClassfiyAadapter;
import com.ledou.mhw.fragment.adapter.ClassfiyBadapter;
import com.ledou.mhw.fragment.adapter.ClassfiyCadapter;
import com.ledou.mhw.fragment.base.Aclass;
import com.ledou.mhw.fragment.base.Bclass;
import com.ledou.mhw.fragment.base.Cclass;
import com.ledou.mhw.httputlis.HttpUrl;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.jsonutlis.Json;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 *
 */
public class ClassfiyView extends Fragment {
    List<Aclass> listleft = new ArrayList<>();
    List<Bclass> listright = new ArrayList<>();
    List<Cclass> listright2 = new ArrayList<>();
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.listview)
    ListView listView;
    RecyclerView leftlistview;
    RecyclerView rightlistview, rightlistview2;
    View view1;
    TostUtlis tostUtlis;
    TextView fenlei, pinpai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classfiyview, container, false);
        view1 = inflater.inflate(R.layout.classfiyview1, null);
        NoteView.inject(this, view);
        tostUtlis = TostUtlis.getTost(getActivity());
        intiview();
        getleft();
        listView.addHeaderView(view1);
        setlist(inflater);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    listleft.clear();
                    getleft();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        return view;
    }


    /**
     * 一级数据
     */
    private void getleft() {
        Map<String, Object> pub_args = new HashMap<>();
        pub_args.put("class_parent_id", "0");
        HttpUtlis.setjson()
                .method("goods.classGet")
                .pub_args(pub_args)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString("数据异常");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONArray array = new JSONArray(response);
                            listleft = JSON.parseArray(array.toString(), Aclass.class);
                            setleft();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 二级分了数据
     *
     * @param map
     */
    private void getrigth(Map map) {
        listright.clear();
        HttpUtlis.setjson()
                .method("goods.classGet")
                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request().body().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.equals("[]")) {
                            fenlei.setVisibility(View.GONE);
                        } else {
                            fenlei.setVisibility(View.VISIBLE);
                        }
                        try {
                            JSONArray array = new JSONArray(response);
                            listright = JSON.parseArray(array.toString(), Bclass.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setright();
                    }
                });

    }

    /**
     * 二级品牌数据
     *
     * @param map
     */
    private void getrigth2(Map map) {
        listright2.clear();
        HttpUtlis.setjson()
                .method("goods.brandGet")
                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("call2", call.request().body() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.equals("[]")) {
                            pinpai.setVisibility(View.GONE);
                        } else {
                            pinpai.setVisibility(View.VISIBLE);
                        }
                        try {
                            JSONArray array = new JSONArray(response);
                            listright2 = JSON.parseArray(array.toString(), Cclass.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setright2();
                    }
                });

    }


    private void intiview() {
        leftlistview = (RecyclerView) view1.findViewById(R.id.left_list);
        rightlistview = (RecyclerView) view1.findViewById(R.id.right_list);
        rightlistview2 = (RecyclerView) view1.findViewById(R.id.right_list2);
        fenlei = (TextView) view1.findViewById(R.id.fenlei);
        pinpai = (TextView) view1.findViewById(R.id.pinpai);


    }

    /**
     * 一级分类
     */
    private void setleft() {
        leftlistview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        leftlistview.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        final ClassfiyAadapter adapter = new ClassfiyAadapter(getActivity(), listleft);
        leftlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Map<String,Object> pub_args = new HashMap<>();
        pub_args.put("class_parent_id",listleft.get(0).getId());
        getrigth(pub_args);

        Map<String,Object> pub_args2 = new HashMap<>();
        pub_args2.put("class_id",listleft.get(0).getId());
        getrigth2(pub_args2);

        adapter.setOnitemcliklistener(new ClassfiyAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Map<String, Object> pub_args = new HashMap<>();
                pub_args.put("class_parent_id", listleft.get(position).getId());
                getrigth(pub_args);
                Map<String, Object> pub_args2 = new HashMap<>();
                pub_args2.put("class_id", listleft.get(position).getId());
                getrigth2(pub_args2);
                adapter.setbg(position);
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void setright() {
        rightlistview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        final ClassfiyBadapter adapter = new ClassfiyBadapter(getActivity(), listright);
        rightlistview.setAdapter(adapter);
    }

    private void setright2() {
        rightlistview2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        final ClassfiyCadapter adapter2 = new ClassfiyCadapter(getActivity(), listright2);
        rightlistview2.setAdapter(adapter2);
    }


    List<String> leimustring = new ArrayList<String>();

    public void setlist(LayoutInflater inflater) {
        leimustring.add("");
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, getActivity(), inflater);
        listView.setAdapter(endadapter);
    }


}
