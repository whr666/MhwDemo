package mhw.ledou.com.mcd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mhw.ledou.com.mcd.IndexListendAdapter;
import mhw.ledou.com.mcd.ListUtils;
import mhw.ledou.com.mcd.ListViewUtlis;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.adapter.ClassfiyAadapter;
import mhw.ledou.com.mcd.fragment.adapter.ClassfiyBadapter;
import mhw.ledou.com.mcd.fragment.adapter.IndexGadapter;
import mhw.ledou.com.mcd.fragment.base.ClassL;
import mhw.ledou.com.mcd.fragment.base.ClassR;
import mhw.ledou.com.mcd.fragment.base.IndexRec;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

/**
 * Created by XIAOXIN on 2017/10/17.
 */
public class ClassfiyView extends android.app.Fragment implements View.OnClickListener {

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout listView;
    RecyclerView left_list;
    RecyclerView right_list2;
    View view;
    View classfiyview1;
    TostUtlis tostUtlis;
    List<ClassL> listleft = new ArrayList<>();
    List<ClassR> listright = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.classfiyview, container, false);
        tostUtlis = new TostUtlis(getActivity());
        classfiyview1 = inflater.inflate(R.layout.classfiyview1, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        listView = (LinearLayout) view.findViewById(R.id.listview);
//        ListViewUtlis.setlist(listView, getActivity());
        getleftdata("0");
        initview();
        setonclik();
        listView.addView(classfiyview1);
        return view;
    }

    private void setonclik() {

    }

    private void initview() {
        left_list = (RecyclerView) classfiyview1.findViewById(R.id.left_list);
        right_list2 = (RecyclerView) classfiyview1.findViewById(R.id.right_list2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                    getleftdata("0");
                }
            }
        });
    }

    private void getleftdata(String gc_parent_id) {
        OkHttpUtils.get()
                .url(HttpUrl.Host + HttpUrl.fenlei + gc_parent_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (TextUtils.isEmpty(response)) {
                            tostUtlis.setString("网络异常");
                        } else {

                            try {
                                JSONObject jsonobject = new JSONObject(response);

                                String error = jsonobject.getString("error");
                                if (error.equals("0")) {
                                    JSONArray array = jsonobject.getJSONArray("list");
                                    listleft = JSON.parseArray(array.toString(), ClassL.class);
                                    left_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    final ClassfiyAadapter adapter = new ClassfiyAadapter(getActivity(), listleft);
                                    left_list.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    getrightdata(listleft.get(0).getGcid());
                                    adapter.setOnitemcliklistener(new ClassfiyAadapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            getrightdata(listleft.get(position).getGcid());
                                            adapter.setbg(position);
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                } else {
                                    tostUtlis.setString(jsonobject.getString("msg"));
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                });


    }


    public void getrightdata(String gc_id) {

        OkHttpUtils.get()
                .url(HttpUrl.Host + HttpUrl.pinpai + gc_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (TextUtils.isEmpty(response)) {
                            tostUtlis.setString("网络异常");
                        } else {
                            try {
                                JSONObject jsonobject = new JSONObject(response);
                                Log.i("jsonobject2", jsonobject + "");
                                String error = jsonobject.getString("error");
                                if (error.equals("0")) {
                                    JSONArray array = jsonobject.getJSONArray("list");
                                    listright = JSON.parseArray(array.toString(), ClassR.class);
                                    right_list2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                                    ClassfiyBadapter adapter = new ClassfiyBadapter(getActivity(), listright);
                                    right_list2.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    adapter.setOnitemcliklistener(new ClassfiyAadapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            tostUtlis.setString(listright.get(position).getGb_name()
                                            );
                                        }
                                    });
                                } else {
                                    tostUtlis.setString(jsonobject.getString("msg"));
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

    }


    @Override
    public void onClick(View v) {

    }


}
