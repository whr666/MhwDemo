package com.ledou.mhw.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ledou.mhw.R;
import com.ledou.mhw.activity.base.Spec1;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/9/29.
 */

public class InfoSpec1Adapter extends RecyclerView.Adapter<InfoSpec1Adapter.ViewHolder> {

    Context context;
    List<Spec1> list;
    int index = -1 ;

    public InfoSpec1Adapter(Context context, List<Spec1> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.infospec1item, parent, false);

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


    public void setbut(int pos){

        this.index = pos;

    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getSku_spec_value());


        if(index == position){
            holder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.infospec1edge));
            holder.textView.setTextColor(context.getResources().getColor(R.color.goodscolor));
        }else{
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


    Onitemlistener listener =null;

    public static interface Onitemlistener {


        void OnItemClick(View v, int pos);


    }


    public void setItmeClick(Onitemlistener listener) {


        this.listener = listener;

    }


}
