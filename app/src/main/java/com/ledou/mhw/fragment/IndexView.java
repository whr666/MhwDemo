package com.ledou.mhw.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ledou.mhw.ListUtils;
import com.ledou.mhw.R;
import com.ledou.mhw.activity.CommodityInfoActivity;
import com.ledou.mhw.activity.SearchActivity;
import com.ledou.mhw.adapter.IndexListendAdapter;
import com.ledou.mhw.fragment.adapter.IndexGadapter;
import com.ledou.mhw.fragment.adapter.IndexHadapter;
import com.ledou.mhw.fragment.base.IndexH;
import com.ledou.mhw.getimg.GlideImageLoader;
import com.ledou.mhw.fragment.base.Banner;
import com.ledou.mhw.getimg.ImgUtils;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.CubeOutTransformer;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 *
 */
public class IndexView extends Fragment implements View.OnClickListener {
    public static IndexView mview;
    Activity activity;
    Context context;
    public List<Banner> banners = new ArrayList<Banner>();
    public List<String> strings = new ArrayList<String>();
    public List<IndexH.Goods> indexHs = new ArrayList<IndexH.Goods>();

    ListView listview;
    SwipeRefreshLayout swRefresh;
    @BindView(R.id.index1Hrecyclerview)
    RecyclerView Hrecyclerview;
    @BindView(R.id.index1Grecyclerview1)
    RecyclerView Grecyclerview1;
    @BindView(R.id.index1Grecyclerview2)
    RecyclerView Grecyclerview2;



    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;




    View luobo;
    View indexview1;
    EditText  indexSearch2;

    TostUtlis tostUtlis;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.indexview, container, false);
        mview  = this;
        activity = getActivity();
        tostUtlis = TostUtlis.getTost(getActivity());
        context = getActivity().getApplicationContext();
        luobo = inflater.inflate(R.layout.luobo,container,false);
        indexview1 = inflater.inflate(R.layout.indexview1,null);
        swRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swRefresh);
        listview = (ListView) view.findViewById(R.id.listview);
        indexSearch2 = (EditText) view.findViewById(R.id.indexSearch2);

        NoteView.inject(this, indexview1);
        indexSearch2.setOnClickListener(this);

        ListUtils.setWH(getActivity(),img1,75,38);
        ListUtils.setWH(getActivity(),img2,75,38);
        ListUtils.setWH(getActivity(),img3,75,38);

        //对于listview，注意添加这一行，即可在item上使用高度
        AutoUtils.autoSize(indexview1);
        AutoUtils.autoSize(luobo);
        getbannerdata();
        getHdata();
        getVdata();
        setlist(inflater);
        listview.addHeaderView(luobo);
        listview.addHeaderView(indexview1);
        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swRefresh.isRefreshing()){
                    banners.clear();
                    indexHs.clear();
                    getbannerdata();
                    getHdata();
                    getVdata();
                    swRefresh.setRefreshing(false);
                }
            }
        });

        return view;
    }


    public void setVrecyclerview(){

        final Intent intent = new Intent();
        Grecyclerview1.setLayoutManager(new GridLayoutManager(getActivity(),2));
        IndexGadapter adapter1 =   new IndexGadapter(LayoutInflater.from(getActivity()),getActivity(),indexHs);
        Grecyclerview1.setAdapter(adapter1);
        adapter1.setOnItemClickListener(new IndexGadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent.setClass(getActivity(), CommodityInfoActivity.class);
                intent.putExtra("id",indexHs.get(position).getId());
                startActivity(intent);
            }
        });




        Grecyclerview2.setLayoutManager(new GridLayoutManager(getActivity(),2));
        IndexGadapter adapter2 =   new IndexGadapter(LayoutInflater.from(getActivity()),getActivity(),indexHs);
        Grecyclerview2.setAdapter(adapter2);
        adapter2.setOnItemClickListener(new IndexGadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent.setClass(getActivity(), CommodityInfoActivity.class);
                intent.putExtra("id",indexHs.get(position).getId());
                startActivity(intent);
                Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void setHrecyclerview() {
        final Intent intent = new Intent();
        LinearLayoutManager manager= new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Hrecyclerview.setLayoutManager(manager);
        IndexHadapter adapter =   new IndexHadapter(LayoutInflater.from(getActivity()),getActivity(),indexHs);
        Hrecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new IndexHadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent.setClass(getActivity(), CommodityInfoActivity.class);
                intent.putExtra("id",indexHs.get(position).getId());
                startActivity(intent);
            }
        });
    }

    /**
     * banner数据
     */
    private void getbannerdata() {
        Map map = new HashMap();
        map.put("position","home");
        HttpUtlis.setjson()
                .method("app.bannerGet")
                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        banners = JSON.parseArray(response,Banner.class);
                        setbanner();
                    }
                });
    }


    /**
     * H数据
     */
    private void getHdata() {
//        Map map = new HashMap();
//        map.put("position","home");
        HttpUtlis.setjson()
                .method("app.specialRecommendGet")
//                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String link = jsonObject.getString("link");
                            String pic = jsonObject.getString("pic");
                            JSONArray goods = jsonObject.getJSONArray("goods");
                            ImgUtils.getimgutils().GetImg(activity,pic,img1);
//                            indexHs = JSON.parseArray(goods.toString(),IndexH.Goods.class);
                            Type type = new TypeToken<List<IndexH.Goods>>(){}.getType();
                            indexHs = new Gson().fromJson(goods.toString(),type);
                            setHrecyclerview();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });
    }


    /**app.bagRecommendGet
     * V数据
     */

    private void getVdata() {
//        Map map = new HashMap();
//        map.put("position","home");
        HttpUtlis.setjson()
                .method("app.specialRecommendGet")
//                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String link = jsonObject.getString("link");
                            String pic = jsonObject.getString("pic");
                            JSONArray goods = jsonObject.getJSONArray("goods");
                            ImgUtils.getimgutils().GetImg(activity,pic,img2);
//                            indexHs = JSON.parseArray(goods.toString(),IndexH.Goods.class);
                            Type type = new TypeToken<List<IndexH.Goods>>(){}.getType();
                            indexHs = new Gson().fromJson(goods.toString(),type);
                            setVrecyclerview();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });
    }



    /**
     * banner设置
     */
    private void setbanner() {
        com.youth.banner.Banner banner = (com.youth.banner.Banner) luobo.findViewById(R.id.banner);
        ListUtils.setWH(getActivity(),banner,75,42);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(banners);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.RotateDown);
        banner.setBannerAnimation(CubeOutTransformer.class);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(strings);
        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                final Intent intent = new Intent();
//                intent.setClass(getActivity(), CommodityInfoActivity.class);
//                startActivity(intent);
            }
        });

        banner.start();



    }




    List<String> leimustring = new ArrayList<String>();
    public void setlist(LayoutInflater inflater){
        leimustring.add("");
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, getActivity(), inflater);
        listview.setAdapter(endadapter);
//	        endadapter.notifyDataSetChanged();
//	        IndexView.setListViewHeightBasedOnChildren(listview);
    }





    /*绑定点击事件*/
//    @BindOnclik({R.id.dianhua,R.id.imagview})

    @Override
    public void onClick(View v){
        Intent intent = new Intent();
            switch (v.getId()){
            case R.id.indexSearch2 :
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
            break;


            default :

            break;
            }
    }


}
