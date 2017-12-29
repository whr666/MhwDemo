package com.ledou.mhw.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ledou.mhw.HomeActivity;
import com.ledou.mhw.R;
import com.ledou.mhw.activity.CommodityInfoActivity;
import com.ledou.mhw.activity.base.AsseSkus;
import com.ledou.mhw.activity.base.Skus;
import com.ledou.mhw.activity.base.Spec1;
import com.ledou.mhw.activity.base.Spec2;
import com.ledou.mhw.httputlis.HttpUtlis;
import com.ledou.mhw.view.widget.TostUtlis;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by XIAOXIN on 2017/10/10.
 */

public class InfoSpec2Adapter extends RecyclerView.Adapter<InfoSpec2Adapter.ViewHolder> {

    Context context;
    List<AsseSkus> list;
    int index = -1;
    public InfoSpec2Adapter(Context context, List<AsseSkus> list) {
        this.context = context;
        this.list = list;
    }

    public void  setList(List<AsseSkus> list){
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.infospec2item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {

                    listener.OnItemClick(v, (Integer) v.getTag());

                }
            }
        });
        return viewHolder;
    }

    InfoSpec1Adapter.Onitemlistener listener = null;

    public static interface Onitemlistener {


        void OnItemClick(View v, int pos);


    }


    public void setItmeClick(InfoSpec1Adapter.Onitemlistener listener) {


        this.listener = listener;

    }



    public void setbut(int pos) {

        this.index = pos;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getSku_spec_value_2());
        if (index == position) {
            holder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.infospec1edge));
            holder.textView.setTextColor(context.getResources().getColor(R.color.goodscolor));
        } else {
            holder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.uninfospec1edge));
            holder.textView.setTextColor(context.getResources().getColor(R.color.goodscolor2));
        }
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.infoitemtext);
        }
    }


}
