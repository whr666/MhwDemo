package com.ledou.mhw.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.ledou.mhw.HomeActivity;
import com.ledou.mhw.ListUtils;
import com.ledou.mhw.R;
import com.ledou.mhw.activity.adapter.InfoSpec1Adapter;
import com.ledou.mhw.activity.adapter.InfoSpec2Adapter;
import com.ledou.mhw.activity.base.AsseSkus;
import com.ledou.mhw.activity.base.Spec1;
import com.ledou.mhw.activity.base.Spec2;
import com.ledou.mhw.adapter.IndexListendAdapter;
import com.ledou.mhw.algorithm.SpecUtlis;
import com.ledou.mhw.fragment.IndexView;
import com.ledou.mhw.getimg.GlideImageLoader;
import com.ledou.mhw.getimg.GlideImageLoaderinfo;
import com.ledou.mhw.getimg.ImgUtils;
import com.ledou.mhw.html.HtmlUtlis;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.jsonutlis.InfoData;
import com.ledou.mhw.status.Status;
import com.ledou.mhw.ui.ShowDialog;
import com.ledou.mhw.view.operation.BindView;
import com.ledou.mhw.view.operation.NoteView;
import com.ledou.mhw.view.widget.TostUtlis;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.CubeOutTransformer;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zzhoujay.richtext.RichText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class CommodityInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static CommodityInfoActivity activity;
    Context context;
    SwipeRefreshLayout refresh;
    ListView listView;
    LayoutInflater inflater;
    View luobo;
    View comview,infodialog;
    TextView buybut1;
    TextView buybut2;
    TextView buybut3;
    String goods_id;
    TostUtlis tostUtlis;
    List<String> bannerlist = new ArrayList<>();
    List<Spec1> spec1s1 = new ArrayList<>();
    List<Spec2> spec1s2 = new ArrayList<>();
    List<AsseSkus> Asseskuss = new ArrayList<>();
    ImageView backimg,infoattention,infoshaer;

    @BindView(R.id.goodstitle)
    TextView goodstitle;
    @BindView(R.id.goodsinfoPrice)
    TextView goodsinfoPrice;
    @BindView(R.id.cankaojia)
    TextView cankaojia;
    @BindView(R.id.yishou)
    TextView yishou;
    @BindView(R.id.yunfei)
    TextView yunfei;
    @BindView(R.id.html)
    TextView webview;

    String skuid="";
    String color="";
    String size="";
     InfoSpec2Adapter  adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_info);
//        setConfigCallback((WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        Status.setstatus(this);
        activity = this;
        context = getApplicationContext();
        inflater = LayoutInflater.from(this);
        luobo = inflater.inflate(R.layout.luobo, null);
        comview = inflater.inflate(R.layout.commodityinfo1, null);
        infodialog = inflater.inflate(R.layout.infodialog, null);
        NoteView.inject(this, comview);
        goods_id = getIntent().getStringExtra("id");
        tostUtlis = TostUtlis.getTost(this);
        initview();
        setonclick();
        getinfodata();
        setlistview();
        listView.addHeaderView(luobo);
        listView.addHeaderView(comview);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refresh.isRefreshing()) {
                    refresh.setRefreshing(false);
                }
            }
        });

    }

    ImageView logimg;
    TextView logname,logxianjia,logyuanjia,logkucun,spec_1_text,spec_2_text;
    public TextView dialogok;
    RecyclerView spec_1_recyc,spec_2_recyc;

    LinearLayout disimi;

    ImageView logjia, logjian;
    EditText logshuliang;

    private void initview() {

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        listView = (ListView) findViewById(R.id.listview);
        buybut1 = (TextView) findViewById(R.id.buybut1);
        buybut2 = (TextView) findViewById(R.id.buybut2);
        buybut3 = (TextView) findViewById(R.id.buybut3);
        backimg = (ImageView) findViewById(R.id.backimg);
        infoattention = (ImageView) findViewById(R.id.infoattention);
        infoshaer = (ImageView) findViewById(R.id.infoshaer);

        logimg = (ImageView) infodialog.findViewById(R.id.logimg);
        logname = (TextView) infodialog.findViewById(R.id.logname);
        logxianjia = (TextView) infodialog.findViewById(R.id.logxianjia);
        logyuanjia = (TextView) infodialog.findViewById(R.id.logyuanjia);
        logyuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        logkucun = (TextView) infodialog.findViewById(R.id.logkucun);

        logjia = (ImageView) infodialog.findViewById(R.id.jia);
        logjian = (ImageView) infodialog.findViewById(R.id.jian);
        logshuliang = (EditText) infodialog.findViewById(R.id.shuliang);

        spec_1_text = (TextView) infodialog.findViewById(R.id.spec_1_text);
        spec_2_text = (TextView) infodialog.findViewById(R.id.spec_2_text);

        disimi = (LinearLayout) infodialog.findViewById(R.id.disimi);
        dialogok = (TextView) infodialog.findViewById(R.id.dialogok);

        spec_1_recyc = (RecyclerView) infodialog.findViewById(R.id.spec_1_recyc);
        spec_2_recyc = (RecyclerView) infodialog.findViewById(R.id.spec_2_recyc);




    }

    private void setonclick() {
        buybut2.setOnClickListener(this);
        buybut1.setOnClickListener(this);
        buybut3.setOnClickListener(this);
        backimg.setOnClickListener(this);
        infoattention.setOnClickListener(this);
        infoshaer.setOnClickListener(this);
        disimi.setOnClickListener(this);
        dialogok.setOnClickListener(this);

        logjia.setOnClickListener(this);
        logjian.setOnClickListener(this);
    }

    /**
     * 设置轮播图
     */
    private void sebanner() {
        {
            Banner banner = (Banner) luobo.findViewById(R.id.banner);
            ListUtils.setWH(activity, banner, 75, 75);
            //设置banner样式
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoaderinfo());
            //设置图片集合
            banner.setImages(bannerlist);
            //设置banner动画效果
//        banner.setBannerAnimation(Transformer.RotateDown);
            banner.setBannerAnimation(CubeOutTransformer.class);
            //设置标题集合（当banner样式有显示title时）
//            banner.setBannerTitles(IndexView.mview.strings);
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
                    final Intent intent = new Intent();
                    intent.setClass(activity, CommodityInfoActivity.class);
                    startActivity(intent);
                    Toast.makeText(activity, position + "", Toast.LENGTH_SHORT).show();
                }
            });

            banner.start();


        }


    }

    /**
     * 添加listview
     */
    private void setlistview() {
        List<String> leimustring = new ArrayList<>();
        leimustring.add("");
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, activity, inflater);
        listView.setAdapter(endadapter);
    }


    /**
     * getdata
     */
    RichText richtext;
     List<AsseSkus>  AsseSkuslist;
    private void getinfodata() {
        Map map = new HashMap();
        map.put("goods_id", goods_id);
        HttpUtlis.setjson()
                .method("goods.info")
                .pub_args(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString("数据异常");
                        tostUtlis.setString(call.request().body().toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                       JSONObject jsonObject = InfoData.getjsonobj(response);
                        if (jsonObject!=null){
                        try {
                       JSONArray images = jsonObject.getJSONArray("images");
                            if (images!=null){
                                ImgUtils.getimgutils().GetImg(activity,images.get(0)+"",logimg);
                                for (int i = 0; i <images.length() ; i++) {
                                    bannerlist.add(images.get(i)+"");
                                }
                                sebanner();
                            }
                       JSONObject goods = jsonObject.getJSONObject("goods");
                            String html =  goods.getString("goods_body");
                            if ( html!= null) {
                              richtext =   RichText.from(html).into(webview);
                            }
                            goods.getString("id");
                            goodstitle.setText(goods.getString("goods_name"));
                            goodsinfoPrice.setText(goods.getString("goods_price"));
                            cankaojia.setText("市场参考价:¥ "+goods.getString("goods_market_price"));
                            yishou.setText("已售"+goods.getString("goods_stock"));
                       JSONObject freight = jsonObject.getJSONObject("freight");
                            yunfei.setText(freight.getString("goods_freight"));
                       JSONObject sku = jsonObject.getJSONObject("sku");
                            JSONArray skus = sku.getJSONArray("skus");
                            JSONObject spec_2 = sku.getJSONObject("spec_2");
                            JSONObject spec_1 = sku.getJSONObject("spec_1");
                            if (spec_1.getString("type").equals("null")||TextUtils.isEmpty(spec_1.getString("type"))){
                                spec_1_text.setVisibility(View.GONE);
                            }else{
                                spec_1_text.setText(spec_1.getString("type"));
                                JSONArray data1 = spec_1.getJSONArray("data");
                                spec1s1 = JSON.parseArray(data1.toString(),Spec1.class);
                                spec_1_recyc.setLayoutManager(new GridLayoutManager(activity,5));
                                final InfoSpec1Adapter  adapter= new InfoSpec1Adapter(activity,spec1s1);
                                spec_1_recyc.setAdapter(adapter);
                                adapter.setItmeClick(new InfoSpec1Adapter.Onitemlistener() {
                                    @Override
                                    public void OnItemClick(View v, int pos) {
                                        AsseSkuslist =  SpecUtlis.getAsseSkus(Asseskuss,spec1s1.get(pos).getId());
                                        AsseSkus asseSkus = SpecUtlis.getsku(Asseskuss,spec1s1.get(pos).getId());
                                        if (TextUtils.isEmpty(skuid)){
                                            skuid = SpecUtlis.getskuid(Asseskuss,spec1s1.get(pos).getId());
                                            color = spec1s1.get(pos).getSku_spec_value();
                                        }else{
                                            color = spec1s1.get(pos).getSku_spec_value();
                                            skuid = SpecUtlis.getskuid(Asseskuss,spec1s1.get(pos).getSku_spec_value(),size);
                                        }
                                        logyuanjia.setText("¥"+asseSkus.getSku_market_price());
                                        logxianjia.setText("¥"+asseSkus.getSku_price());
                                        logkucun.setText("库存:"+asseSkus.getSku_stock());
                                        ImgUtils.getimgutils().GetImg(activity,spec1s1.get(pos).getSku_spec_pic(),logimg);
                                        if (AsseSkuslist.size()!=0){
                                            adapter2.setList(AsseSkuslist);
                                            adapter2.notifyDataSetChanged();
                                        }else{
                                            adapter2.setList(AsseSkuslist);
                                            spec_2_text.setVisibility(View.GONE);
                                            spec_2_text.setText("数量");
                                        }
                                        adapter.setbut(pos);
                                        adapter.notifyDataSetChanged();
                                    }
                                });

                                if (spec_2.getString("type").equals("null") || TextUtils.isEmpty(spec_2.getString("type"))){
                                    spec_2_text.setText("数量");
                                    spec_2_text.setVisibility(View.GONE);
                                    Asseskuss = JSON.parseArray(skus.toString(),AsseSkus.class);
                                }else{
                                    if (spec_1.getString("type").equals("null")||TextUtils.isEmpty(spec_1.getString("type"))){
                                        spec_2_text.setText(spec_2.getString("type"));
                                        Asseskuss = JSON.parseArray(skus.toString(),AsseSkus.class);
                                    }else{
                                        spec_2_text.setText(spec_2.getString("type"));
                                        Asseskuss = JSON.parseArray(skus.toString(),AsseSkus.class);
                                        AsseSkuslist =  SpecUtlis.getAsseSkus(Asseskuss,spec1s1.get(0).getId());
                                        spec_2_recyc.setLayoutManager(new GridLayoutManager(activity,5));
                                        adapter2 = new InfoSpec2Adapter(activity,AsseSkuslist);
                                        spec_2_recyc.setAdapter(adapter2);
                                        adapter2.setItmeClick(new InfoSpec1Adapter.Onitemlistener() {
                                            @Override
                                            public void OnItemClick(View v, int pos) {
                                                if (TextUtils.isEmpty(skuid)){
                                                    skuid = SpecUtlis.getskuid(Asseskuss, AsseSkuslist.get(pos).getSku_spec_value_2());
                                                    size = AsseSkuslist.get(pos).getSku_spec_value_2();
                                                }else{
                                                    size = AsseSkuslist.get(pos).getSku_spec_value_2();
                                                    skuid = SpecUtlis.getskuid(Asseskuss,AsseSkuslist.get(pos).getSku_spec_value_2());
                                                }
                                                adapter2.setbut(pos);
                                                adapter2.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                            logname.setText(goods.getString("goods_name"));
                            logkucun.setText("库存:"+goods.getString("goods_sale_num"));
                            logxianjia.setText("¥"+goods.getString("goods_price"));
                            logyuanjia.setText("¥"+goods.getString("goods_market_price"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else{
                            tostUtlis.setString("网络异常");
                        }
                }
                         }

                    );
    }

    Dialog dialog = null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buybut2:
                if (dialog==null){
                    dialog = ShowDialog.setview(activity,infodialog);
                }else{
                    dialog.show();
                }
                break;
            case R.id.disimi:
                if (dialog!=null){
                    dialog.dismiss();
                }else{
                    dialog = ShowDialog.setview(activity,infodialog);
                }
                break;
            case R.id.dialogok:
                    //一次只添加一个商品规格，只用做添加
                    Map<String,List<Map>> map = new HashMap();
                    List<Map> maplist = new ArrayList<Map>();
                    Map map1 = new HashMap();
                            map1.put("sku_id",skuid);
                            map1.put("buy_num",logshuliang.getText().toString());
                            maplist.add(map1);
                    map.put("sku_items",maplist);
                    setshopcar(map);
                    Log.i("map",map+"");
                break;
            case R.id.jian:
                if (logshuliang.getText().toString().equals("0")) {
                    tostUtlis.setString("数量不能少于0");
                } else {
                    logshuliang.setText(Integer.parseInt(logshuliang.getText().toString()) - 1 + "");
                }
                break;
            case R.id.jia:
                if (logshuliang.getText().toString().equals("库存")) {
                    tostUtlis.setString("没有更多货了");
                } else {
                    logshuliang.setText(Integer.parseInt(logshuliang.getText().toString()) + 1 + "");
                }
                break;



            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
//        richtext.clear();
//        richtext = null;
        super.onDestroy();
    }

    public void setConfigCallback(WindowManager windowManager) {
        try {
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field = field.getType().getDeclaredField("mBrowserFrame");
            field = field.getType().getDeclaredField("sConfigCallback");
            field.setAccessible(true);
            Object configCallback = field.get(null);
            if (null == configCallback) {
                return;
            }
            field = field.getType().getDeclaredField("mWindowManager");
            field.setAccessible(true);
            field.set(configCallback, windowManager);
        } catch(Exception e) {
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (dialog!=null){
            dialog.dismiss();
        }else{

        }
        return super.onKeyDown(keyCode, event);
    }



    public void setshopcar(Map map){
        HttpUtlis.setjson()
                .method("cart.goodsAdd")
                .pub_args(map)
                .token(HomeActivity.activity.user.getString("token",""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("call",call.request()+"");
                        tostUtlis.setString(call.request()+"");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            tostUtlis.setString(jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }







}
