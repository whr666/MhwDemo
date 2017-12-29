package mhw.ledou.com.mcd.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.CubeOutTransformer;
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
import mhw.ledou.com.mcd.activity.CommodityInfoActivity;
import mhw.ledou.com.mcd.fragment.adapter.IndexGadapter;
import mhw.ledou.com.mcd.fragment.base.IndexRec;
import mhw.ledou.com.mcd.getimg.GlideImageLoader;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

/**
 * Created by XIAOXIN on 2017/10/17.
 */
public class IndexView extends Fragment {
    View view;
    View luobo;
    View indexview1;
    View indexview2;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ListView listview;
    Banner banner;

    TostUtlis tostUtlis;

    List<IndexRec> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.indexview, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swRefresh);
        listview = (ListView) view.findViewById(R.id.listview);
        tostUtlis = new TostUtlis(getActivity());
        luobo = inflater.inflate(R.layout.luobo, container, false);
        indexview1 = inflater.inflate(R.layout.indexview1, container, false);
        indexview2 = inflater.inflate(R.layout.indexview2, container, false);
        recyclerView = (RecyclerView) indexview2.findViewById(R.id.index1Grecyclerview1);
        ListViewUtlis.setlist(listview,getActivity());
        setbanner();
        intiview();
        setonclik();
        gettada();
        listview.addHeaderView(luobo);
        listview.addHeaderView(indexview1);
        listview.addHeaderView(indexview2);
        return view;
    }

    private void intiview() {


    }

    private void setonclik() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                    gettada();

                }
            }
        });
    }


    public void gettada() {

        OkHttpUtils
                .get()
                .url(HttpUrl.Host+HttpUrl.tuijian)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request()+"");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            if (TextUtils.isEmpty(response)){
                                tostUtlis.setString("网络异常");
                            }else{
                                JSONObject jsonobject = new JSONObject(response);
                               String error =  jsonobject.getString("error");
                                if (error.equals("0")){

                                    JSONArray array = jsonobject.getJSONArray("list");
                                  list =  JSON.parseArray(array.toString(), IndexRec.class);
                                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                    IndexGadapter adapter = new IndexGadapter(getActivity(),list);
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    adapter.setOnItemClickListener(new IndexGadapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Intent intent = new Intent(getActivity(), CommodityInfoActivity.class);
                                            intent.putExtra("id",list.get(position).getGid());
                                            startActivity(intent);
                                        }
                                    });

                                }else{
                                    tostUtlis.setString(jsonobject.getString("msg"));
                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    List<Uri> banners = new ArrayList<>();
    /**
     * banner设置
     */
    private void setbanner() {
        Uri uri = Uri.parse("android.resource://package_name/raw/placenumber.png");
        banners.add(uri);
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





}
