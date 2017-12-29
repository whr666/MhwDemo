package mhw.ledou.com.mcd.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.CubeOutTransformer;
import com.zhy.http.okhttp.OkHttpUtils;
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

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.IndexListendAdapter;
import mhw.ledou.com.mcd.ListUtils;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.adapter.InfoSpec1Adapter;
import mhw.ledou.com.mcd.activity.adapter.InfoSpec2Adapter;
import mhw.ledou.com.mcd.activity.base.Infomodel;
import mhw.ledou.com.mcd.activity.base.Spec1;
import mhw.ledou.com.mcd.activity.base.Spec2;
import mhw.ledou.com.mcd.algorithm.SpecUtlis;
import mhw.ledou.com.mcd.fragment.adapter.IndexGadapter;
import mhw.ledou.com.mcd.fragment.base.IndexRec;
import mhw.ledou.com.mcd.getimg.GlideImageLoaderinfo;
import mhw.ledou.com.mcd.getimg.ImgUtils;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.status.Status;
import mhw.ledou.com.mcd.ui.ShowDialog;
import mhw.ledou.com.mcd.view.operation.BindView;
import mhw.ledou.com.mcd.view.operation.NoteView;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class CommodityInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static CommodityInfoActivity activity;
    Context context;
    SwipeRefreshLayout refresh;
    ListView listView;
    LayoutInflater inflater;
    View luobo;
    View comview, infodialog;
    TextView buybut3;
    String goods_id = "";
    String skuid = "";
    String unm= "";
    String color= "";
    String kuncun= "";

    List<Spec1> spec1s1 = new ArrayList<>();
    List<Spec2> spec1s2 = new ArrayList<>();

    TostUtlis tostUtlis;
    List<String> bannerlist = new ArrayList<>();
    ImageView backimg, infoattention, infoshaer;

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

    List<mhw.ledou.com.mcd.model.base.Banner> banners = new ArrayList<>();

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
        getdata();
        setonclick();
        setlistview();
        listView.addHeaderView(luobo);
        listView.addHeaderView(comview);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refresh.isRefreshing()) {
                    refresh.setRefreshing(false);
                    banners.clear();
                    getdata();
                }
            }
        });

    }

    ImageView logimg;
    public TextView logname, logxianjia, logyuanjia, logkucun, spec_1_text, spec_2_text;
    public TextView dialogok;
    RecyclerView spec_1_recyc, spec_2_recyc;

    LinearLayout disimi;

    ImageView logjia, logjian;
    EditText logshuliang;

    private void initview() {

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        listView = (ListView) findViewById(R.id.listview);
        buybut3 = (TextView) findViewById(R.id.buybut3);
        backimg = (ImageView) findViewById(R.id.backimg);
        infoattention = (ImageView) findViewById(R.id.infoattention);
        infoshaer = (ImageView) findViewById(R.id.infoshaer);

        logimg = (ImageView) infodialog.findViewById(R.id.logimg);
        logname = (TextView) infodialog.findViewById(R.id.logname);
        logxianjia = (TextView) infodialog.findViewById(R.id.logxianjia);
        logyuanjia = (TextView) infodialog.findViewById(R.id.logyuanjia);
        logyuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
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
            banner.setImages(banners);
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
//                    final Intent intent = new Intent();
//                    intent.setClass(activity, CommodityInfoActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(activity, position + "", Toast.LENGTH_SHORT).show();
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
        IndexListendAdapter endadapter = new IndexListendAdapter(leimustring, activity);
        listView.setAdapter(endadapter);
    }


    Dialog dialog = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buybut3:
                if (dialog == null) {
                    dialog = ShowDialog.setview(activity, infodialog);
                } else {
                    dialog.show();
                }
                break;
            case R.id.disimi:
                if (dialog != null) {
                    dialog.dismiss();
                } else {
                    dialog = ShowDialog.setview(activity, infodialog);
                }
                break;
            case R.id.dialogok:
                if (dialog != null) {
                    if (spec1s1.toString().equals("")){
                        addshopcar();
                    }else{
                        if (skuid.equals("")){
                            tostUtlis.setString("规格不能为空");
                        }else{
                            addshopcar();
                        }
                    }
//
                    dialog.dismiss();
                } else {
                    dialog = ShowDialog.setview(activity, infodialog);
                }
                break;
            case R.id.jian:
                if (logshuliang.getText().toString().equals("0")) {
                    tostUtlis.setString("数量不能少于0");
                } else {
                    logshuliang.setText(Integer.parseInt(logshuliang.getText().toString()) - 1 + "");
                }
                break;
            case R.id.jia:
                if (logshuliang.getText().toString().equals(kuncun)) {
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
        } catch (Exception e) {
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (dialog != null) {
            dialog.dismiss();
        } else {

        }
        return super.onKeyDown(keyCode, event);
    }


    public void getdata() {
        OkHttpUtils.get()
                .url(HttpUrl.Host + HttpUrl.xiangqing + goods_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tostUtlis.setString(call.request() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            if (TextUtils.isEmpty(response)) {
                                tostUtlis.setString("网络异常");
                            } else {
                                JSONObject jsonobject = new JSONObject(response);
                                String error = jsonobject.getString("error");
                                if (error.equals("0")) {
                                    JSONObject info = jsonobject.getJSONObject("info");
                                    Infomodel infomodel = JSON.parseObject(info.toString(), Infomodel.class);
                                    mhw.ledou.com.mcd.model.base.Banner banner = new mhw.ledou.com.mcd.model.base.Banner();
                                    banner.setImg(infomodel.getGoods_image());
                                    banners.add(banner);
                                    sebanner();
                                    setdata(infomodel);
                                    if (!TextUtils.isEmpty(infomodel.getGoods_spec().toString())) {
                                        spec1s1 = SpecUtlis.getColorList(infomodel.getGoods_spec());
                                        spec1s2 = SpecUtlis.getSizeList(infomodel.getGoods_spec());
                                        setspec1(infomodel);
                                        setspec2(infomodel);
                                    }
                                } else {
                                    tostUtlis.setString(jsonobject.getString("msg"));
                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                });


    }

    public void setspec1(final Infomodel infomodel) {
        //颜色
        if (!TextUtils.isEmpty(spec1s1.toString()) && !spec1s1.toString().trim().equals("")) {
            spec_1_recyc.setLayoutManager(new GridLayoutManager(activity, 4));
            final InfoSpec1Adapter adapter1 = new InfoSpec1Adapter(activity, spec1s1);
            spec_1_recyc.setAdapter(adapter1);
            adapter1.setItmeClick(new InfoSpec1Adapter.Onitemlistener() {
                @Override
                public void OnItemClick(View v, int pos) {
                    spec1s2.clear();
                    adapter1.setbut(pos);
                    adapter1.notifyDataSetChanged();
                    color = spec1s1.get(pos).getColor();
                    skuid = SpecUtlis.getSkuid(infomodel.getGoods_spec(), color);
                    spec1s2 = SpecUtlis.getSizeList(infomodel.getGoods_spec(), color);
                    setspec2(infomodel);
                }
            });
        }

    }

    public void setspec2(final Infomodel infomodel) {
        //尺码
        if (!TextUtils.isEmpty(spec1s2.toString()) && !spec1s2.toString().equals("")) {
            spec_2_recyc.setLayoutManager(new GridLayoutManager(activity, 4));
            final InfoSpec2Adapter adapter2 = new InfoSpec2Adapter(activity, spec1s2);
            spec_2_recyc.setAdapter(adapter2);
            adapter2.setItmeClick(new InfoSpec2Adapter.Onitemlistener() {
                @Override
                public void OnItemClick(View v, int pos) {
                    adapter2.setbut(pos);
                    adapter2.notifyDataSetChanged();
                    skuid = SpecUtlis.getSkuid(infomodel.getGoods_spec(), color, spec1s2.get(pos).getSize());
                }
            });
        } else {
            spec_2_text.setVisibility(View.GONE);
            spec_2_recyc.setVisibility(View.GONE);
        }

    }

    RichText richtext;

    private void setdata(Infomodel infomodel) {
        goodstitle.setText(infomodel.getGoods_name());
        goodsinfoPrice.setText(infomodel.getGoods_price());
        cankaojia.setText("税后价:¥" + infomodel.getGoods_price_tax());
        yishou.setText("库存:" + infomodel.getGoods_storage());
        kuncun = infomodel.getGoods_storage();
        richtext = RichText.from(infomodel.getGoods_body()).into(webview);

        ImgUtils.getimgutils().GetImg(activity, infomodel.getGoods_image(), logimg);
        logname.setText(infomodel.getGoods_name());
        logkucun.setText("库存:" + infomodel.getGoods_storage());
        logxianjia.setText("税后价:¥" + infomodel.getGoods_price_tax());
        logyuanjia.setText("税前价:¥" + infomodel.getGoods_price());
    }


    public void addshopcar() {
        unm = logshuliang.getText().toString();
        if (skuid==null){
            skuid = "0";
        }
        OkHttpUtils.get()
                .url(HttpUrl.Host+"cart?act=add&gid=" + goods_id + "&spec=" + skuid + "&num=" + unm + "&access_token=" + HomeActivity.activity.user.getString("token", ""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            tostUtlis.setString(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


}
