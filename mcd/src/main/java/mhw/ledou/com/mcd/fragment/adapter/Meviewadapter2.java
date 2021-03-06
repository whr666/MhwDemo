package mhw.ledou.com.mcd.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.base.MeModel;

/**
 * Created by XIAOXIN on 2017/9/14.
 */

public class Meviewadapter2 extends RecyclerView.Adapter<Meviewadapter2.ViewHolder> {
LayoutInflater inflater;
    Context context;
    List<MeModel> list;

    public Meviewadapter2(LayoutInflater inflater, Context context, List<MeModel> list) {
        this.inflater = inflater;
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.img);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  inflater.inflate(R.layout.meviewitem2,parent,false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null){

                    listener.onItemClick(v, (Integer) v.getTag());

                }

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.img.setImageResource(list.get(position).getImg());
        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    OnItemClickListener listener = null;

    //添加item点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;

    }


}
