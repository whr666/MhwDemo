package mhw.ledou.com.mcd.fragment.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import mhw.ledou.com.mcd.HomeActivity;
import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.activity.SubmitOrderActivity;
import mhw.ledou.com.mcd.fragment.ShopcartView;
import mhw.ledou.com.mcd.fragment.base.ShopingModel;
import mhw.ledou.com.mcd.getimg.ImgUtils;
import mhw.ledou.com.mcd.httputlis.HttpUrl;
import mhw.ledou.com.mcd.model.ShopingCartRule;
import mhw.ledou.com.mcd.view.widget.TostUtlis;
import okhttp3.Call;

public class ShopingCartAdapter extends BaseAdapter {

    List<ShopingModel> mListGoods;
    List<CheckBox> xzlist = new ArrayList<CheckBox>();
    List<ImageView> jialist = new ArrayList<>();
    List<ImageView> jianlist = new ArrayList<>();
    List<TextView> jjshulist2 = new ArrayList<TextView>();
    List<EditText> jjshulist = new ArrayList<EditText>();
    LayoutInflater inflater;
    Context context;
    boolean isSelectAll = false;
    boolean isChildSelected = false;
    holdview hold;
    ShopingModel sm;
    String cart_id, num;
    int con;
    String xianshi;
    //创建一个集合 去记录选中与未选中的状态
    LinkedList<Boolean> linkedList = new LinkedList<Boolean>();
    TostUtlis tostUtlis;


    //对外提供一个方法 获取这个集合
    @SuppressWarnings("unused")
    public List<Boolean> getSelect() {
        return linkedList;
    }

    public ShopingCartAdapter(Context context, List<ShopingModel> mListGoods, String xianshi) {
        super();
        this.mListGoods = mListGoods;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.xianshi = xianshi;
        this.tostUtlis = TostUtlis.getTost(context);

        for (int i = 0; i < mListGoods.size(); i++) {
            linkedList.add(false);
        }

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mListGoods.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mListGoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub
        sm = mListGoods.get(position);
        if (view == null) {
            hold = new holdview();
            view = inflater.inflate(R.layout.shoppingitme, null);
            hold.shopxuanze = (CheckBox) view.findViewById(R.id.shopxuanze);
            hold.shopitmeimg = (ImageView) view.findViewById(R.id.shopitmeimg);
            hold.shopitmename = (TextView) view.findViewById(R.id.shopitmename);
            hold.shopx = (TextView) view.findViewById(R.id.shopx);
            hold.shopitmeguige = (TextView) view.findViewById(R.id.shopitmeguige);
            hold.shopitmejiage = (TextView) view.findViewById(R.id.shopitmejiage);
            hold.shopitmeshuliang = (TextView) view.findViewById(R.id.shopitmeshuliang);

            hold.include = view.findViewById(R.id.shopinclude);
            hold.jia = (ImageView) hold.include.findViewById(R.id.jia);
            hold.jian = (ImageView) hold.include.findViewById(R.id.jian);
            hold.jjshu = (EditText) hold.include.findViewById(R.id.shuliang);


            TextView jia;
            TextView jian;
            TextView jjshu;
            View include;
            view.setTag(hold);
        } else {
            hold = (holdview) view.getTag();
        }

        xzlist.add(hold.shopxuanze);
        jialist.add(hold.jia);
        jianlist.add(hold.jian);
        jjshulist.add(hold.jjshu);
        jjshulist2.add(hold.shopitmeshuliang);

        ImgUtils.getimgutils().GetImg(context, sm.getGoods_image(), hold.shopitmeimg);
        hold.shopitmename.setText(sm.getGoods_name());
        hold.shopitmeguige.setText(sm.getGoods_spec());
        hold.shopitmejiage.setText("¥" + sm.getGoods_price());

        if (xianshi.equals("shopcar")) {
            hold.shopxuanze.setVisibility(View.VISIBLE);
        } else {
            hold.shopxuanze.setVisibility(View.GONE);
        }

        //编辑按钮刷新
        if (sm.isEditing() == true) {
            ShopcartView.mview.shopingtext1.setText("完成");
            ShopcartView.mview.jiesuan.setText("删除");
            hold.shopitmeshuliang.setVisibility(View.GONE);
            hold.include.setVisibility(View.VISIBLE);
            hold.shopx.setVisibility(View.GONE);
            hold.jjshu.setText(sm.getGoods_num());
            hold.shopitmeshuliang.setText(sm.getGoods_num());
        } else {
            ShopcartView.mview.shopingtext1.setText("编辑");
            ShopcartView.mview.jiesuan.setText("去结算");
            hold.shopitmeshuliang.setVisibility(View.VISIBLE);
            hold.include.setVisibility(View.GONE);
            hold.shopx.setVisibility(View.VISIBLE);
            hold.shopitmeshuliang.setText(sm.getGoods_num());
        }


        jialist.get(position).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                con = Integer.parseInt(jjshulist.get(position).getText().toString());
                con++;
                jjshulist.get(position).setText(Integer.toString(con));
                setshuliang(mListGoods.get(position).getCid(), jjshulist.get(position).getText().toString(), position);
            }
        });

        jianlist.get(position).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                con = Integer.parseInt(jjshulist.get(position).getText().toString());
                if (con <= 1) {
                    tostUtlis.setString("数量不能少于1");
                } else {
                    con--;
                    jjshulist.get(position).setText(Integer.toString(con));
                    setshuliang(mListGoods.get(position).getCid(), jjshulist.get(position).getText().toString(), position);
                }
            }
        });

        xzlist.get(position).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //单选的时候选中的改成相反的值
                if (linkedList.get(position).equals(true)) {
                    linkedList.set(position, false);
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                } else {
                    linkedList.set(position, true);
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                }
                //单选的同时还要去判断是否全选
                if (linkedList.contains(false)) {
                    ShopcartView.mview.ivSelectAll.setChecked(false);
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                } else {
                    ShopcartView.mview.ivSelectAll.setChecked(true);
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                }
                notifyDataSetChanged();
            }
        });


        //结算/删除
        ShopcartView.mview.jiesuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (sm.isEditing() == false) {
                    if (ShopingCartRule.hasSelectedGoods(mListGoods, linkedList, position)) {
                        // TODO Auto-generated method stub
                        ArrayList<String> array = new ArrayList<String>();
                        List<ShopingModel> arraylist = new ArrayList<ShopingModel>();
                        String qian = "0";
                        for (int i = 0; i < linkedList.size(); i++) {
                            if (linkedList.get(i).equals(true)) {
                                array.add(mListGoods.get(i).getCid());
                                arraylist.add(mListGoods.get(i));
                                String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, i);
                                qian = infos;
                            }
                        }
	          	Intent intent = new Intent(context,SubmitOrderActivity.class);
	          	intent.putStringArrayListExtra("array", array);
	          	intent.putExtra("list", (Serializable)arraylist);
	          	intent.putExtra("qian", qian);
	          	context.startActivity(intent);
                    } else {
                        tostUtlis.setString("亲，先选择商品！");
                    }
                } else {
                    if (ShopingCartRule.hasSelectedGoods(mListGoods, linkedList, position)) {
                        ArrayList<String> array = new ArrayList<String>();
                        for (int i = 0; i < linkedList.size(); i++) {
                            if (linkedList.get(i).equals(true)) {
                                array.add(mListGoods.get(i).getCid());
                            }
                        }

                        deleteshop(array);

                    } else {
                        tostUtlis.setString("亲，先选择商品！");
                    }


                }


            }
        });


        //编辑
        ShopcartView.mview.shopingtext1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sm.isEditing() == false) {
                    for (int i = 0; i < mListGoods.size(); i++) {
                        mListGoods.get(i).setEditing(true);
                    }
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                    notifyDataSetChanged();
                } else {
                    for (int i = 0; i < mListGoods.size(); i++) {
                        mListGoods.get(i).setEditing(false);
                    }
                    ShopcartView.mview.getdata();
                    String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                    ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                    notifyDataSetChanged();
                }
            }
        });

        hold.shopxuanze.setChecked(linkedList.get(position));
        //全选
        ShopcartView.mview.ivSelectAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean flag = ShopcartView.mview.ivSelectAll.isChecked();
                for (int i = 0; i < mListGoods.size(); i++) {
                    linkedList.set(i, flag);
                }
                String infos = ShopingCartRule.getShoppingCount(mListGoods, linkedList, position);
                ShopcartView.mview.tvCountMoney.setText("应付金额：¥ " + infos);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public class holdview {
        CheckBox shopxuanze;
        ImageView shopitmeimg;
        TextView shopitmename;
        TextView shopitmeguige;
        TextView shopitmejiage;
        TextView shopx;
        TextView shopitmeshuliang;
        ImageView jia;
        ImageView jian;
        EditText jjshu;
        View include;

    }


    public void setshuliang(String cart_id, String num, final int position) {

        OkHttpUtils.get()
                .url(HttpUrl.Host + "cart?act=change-num&cid=" + cart_id + "&num=" + num + "&access_token=" + HomeActivity.activity.user.getString("token", ""))
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
                            tostUtlis.setString(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


    public void deleteshop(ArrayList<String> array) {
        OkHttpUtils.get()
                .url(HttpUrl.Host + "cart?act=remove&cid="+array+"&access_token="+HomeActivity.activity.user.getString("token",""))
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
