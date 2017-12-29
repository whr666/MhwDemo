package mhw.ledou.com.mcd.fragment;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.LoginActivity;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.AddressEditActivity;
import mhw.ledou.com.mcd.fragment.adapter.Meviewadapter2;
import mhw.ledou.com.mcd.fragment.base.MeModel;
import mhw.ledou.com.mcd.getimg.ImgUtils;
import mhw.ledou.com.mcd.view.operation.BindView;
import mhw.ledou.com.mcd.view.operation.NoteView;
import mhw.ledou.com.mcd.view.widget.DividerGridItemDecoration;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

/**
 * Created by XIAOXIN on 2017/10/17.
 */
public class MeView extends android.app.Fragment implements View.OnClickListener {
    public static MeView meView;
    Activity activity;
    LayoutInflater inflater;
    LinearLayout linearLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    View meview1;
    TostUtlis tostUtlis;
    List<MeModel> list = new ArrayList<>();
    List<MeModel> list2 = new ArrayList<>();
    @BindView(R.id.meRecyclerview)
    RecyclerView meRecyclerview;
    @BindView(R.id.meRecyclerview2)
    RecyclerView meRecyclerview2;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.headimg)
    ImageView headimg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.meview, container, false);
        meview1 = inflater.inflate(R.layout.meview1, container, false);
        this.inflater = inflater;
        tostUtlis = new TostUtlis(getActivity());
        meView = this;
        activity = getActivity();
        linearLayout = (LinearLayout) view.findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        NoteView.inject(this, meview1);
        linearLayout.addView(meview1);
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


    private void setonclik() {

        logintext.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.logintext:
                if (HomeActivity.activity.user.getString("token", "").equals("")) {
                    intent.setClass(activity, LoginActivity.class);
                    startActivity(intent);
                } else {
                    tostUtlis.setString(logintext.getText().toString());
                }
                break;
            default:
                break;
        }
    }


    /**
     * 选项
     */
    int[] imgs = {
            R.mipmap.shoppingcart, R.mipmap.notability, R.mipmap.shippingaddress
            , R.mipmap.feedback, R.mipmap.contactus
    };
    String[] string = {"购物车", "关注", "收货地址", "意见反馈", "联系我们"
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
                Intent intent = new Intent();
                if (position == 2) {
                    intent.setClass(getActivity(), AddressEditActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        if (HomeActivity.activity.user.getString("token", "").equals("")) {
            logintext.setText("登录/注册");
        } else {
            getuserifo();
        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        if (hidden) {
            //被覆盖时

        } else {
            //出现在上面时
            this.onResume();
        }

        super.onHiddenChanged(hidden);
    }


    void getuserifo() {

        OkHttpUtils.get()
                .url("http://caigou.mcdsh.com/app/user?act=info&access_token=" + HomeActivity.activity.user.getString("token", ""))
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
//                        "uid":10027,
//                                "name":"爱上的好看",
//                                "avatar":"http://caigou.mcdsh.com/images/site/avatar.png",
//                                "phone":"13956945835",
//                                "status":1,
//                                "gender":0,
//                                "email":"",
//                                "qq":"",
//                                "weichat":""
                            if (jsonObject.getString("error").equals("0")) {
                                JSONObject info = jsonObject.getJSONObject("info");
                                logintext.setText(info.getString("name"));
                                ImgUtils.getimgutils().GetImg(getActivity(), info.getString("avatar"), headimg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


}
