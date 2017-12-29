package com.ledou.mhw.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ledou.mhw.HomeActivity;
import com.ledou.mhw.R;
import com.ledou.mhw.activity.LoginActivity;
import com.ledou.mhw.activity.LogoutActivity;
import com.ledou.mhw.adapter.IndexListendAdapter;
import com.ledou.mhw.fragment.adapter.Meviewadapter1;
import com.ledou.mhw.fragment.adapter.Meviewadapter2;
import com.ledou.mhw.fragment.base.MeModel;
import com.ledou.mhw.getimg.ImgUtils;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.DividerGridItemDecoration;
import com.ledou.mhw.view.widget.TostUtlis;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
public class MeView extends Fragment implements View.OnClickListener {
    public static MeView meView;
    Activity activity;
    TostUtlis tostUtlis;
    List<MeModel> list = new ArrayList<>();
    List<MeModel> list2 = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    LayoutInflater inflater;
    @BindView(R.id.meRecyclerview)
    RecyclerView meRecyclerview;
    @BindView(R.id.meRecyclerview2)
    RecyclerView meRecyclerview2;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.dengji)
    TextView dengji;
    @BindView(R.id.headimg)
    ImageView headimg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meview, container, false);
        View meview1 = inflater.inflate(R.layout.meview1, null);
        NoteView.inject(this, meview1);
        this.inflater = inflater;
        tostUtlis = new TostUtlis(getActivity());
        meView = this;
        activity = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        listView.addHeaderView(meview1);
        setlist(inflater);
        setdata();
        setdata2();
        setonclik();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        return view;
    }


    public void setonclik() {
        logintext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.logintext:
                if (HomeActivity.activity.user.getString("token","").equals("")) {
                    intent.setClass(activity, LoginActivity.class);
                    startActivity(intent);
                }else{
                    intent.setClass(activity, LogoutActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 订单分类
     */
    int[] img = {R.mipmap.obliga, R.mipmap._sendgood, R.mipmap.shipped, R.mipmap.returngood};
    String[] text = {"待付款", "待发货", "已发货", "退货售后"};

    private void setdata() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        meRecyclerview.setLayoutManager(llm);
        for (int i = 0; i < img.length; i++) {
            MeModel mm = new MeModel();
            mm.setImg(img[i]);
            mm.setTitle(text[i]);
            list.add(mm);
        }
        Meviewadapter1 adapter = new Meviewadapter1(inflater, getActivity(), list);
        meRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new Meviewadapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    /**
     * 选项
     */
    int[] imgs = {
            R.mipmap.account, R.mipmap.shipping_address, R.mipmap.hotline
            , R.mipmap.adout_us, R.mipmap.help
    };
    String[] string = {"账户管理", "收货地址", "咨询电话", "关于我们", "帮助"
    };

    private void setdata2() {
        meRecyclerview2.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        meRecyclerview2.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        for (int i = 0; i < imgs.length; i++) {
            MeModel mm = new MeModel();
            mm.setImg(imgs[i]);
            mm.setTitle(string[i]);
            list2.add(mm);
        }
        Meviewadapter2 adapter = new Meviewadapter2(inflater, getActivity(), list2);
        meRecyclerview2.setAdapter(adapter);
        adapter.setOnItemClickListener(new Meviewadapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    List<String> leimustring = new ArrayList<String>();

    public void setlist(LayoutInflater inflater) {
        leimustring.add("");
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, getActivity(), inflater);
        listView.setAdapter(endadapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        if(HomeActivity.activity.user.getString("token", "").equals("")){

            logintext.setText("登录/注册");

        }else{

            getuser();


        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        if(hidden){
            //被覆盖时

        }else{
            //出现在上面时
            this.onResume();
        }

        super.onHiddenChanged(hidden);
    }


  public  void  getuser(){
      Map map = new HashMap();
      HttpUtlis.setjson()
              .method("user.info")
              .token(HomeActivity.activity.user.getString("token",""))
              .pub_args(map)
              .build()
              .execute(new StringCallback() {
                  @Override
                  public void onError(Call call, Exception e, int id) {
                  tostUtlis.setString(call.request()+"");
                  }
                  @Override
                  public void onResponse(String response, int id) {
//                          "name":"daley",
//                              "mob_phone":"13956945835",
//                              "avatar":"http://meihe.cc/storage/avatar/system/default.png",
//                              "balance":"0.00"
                      try {
                          JSONObject jsonObject = new JSONObject(response);
                         String name = jsonObject.getString("name");
                          String mob_phone = jsonObject.getString("mob_phone");
                          String avatar = jsonObject.getString("avatar");
                          String balance = jsonObject.getString("balance");
                          logintext.setText(name);
                          dengji.setText("等级"+balance);
                          ImgUtils.getimgutils().GetImg(getActivity(),avatar,headimg);
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }

                  }
              });

  }





}
