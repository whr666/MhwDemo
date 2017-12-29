package mhw.ledou.com.mcd.fragment.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.base.IndexRec;
import mhw.ledou.com.mcd.getimg.ImgUtils;

/**
 * Created by XIAOXIN on 2017/9/14.
 */

public class IndexGadapter extends RecyclerView.Adapter<IndexGadapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    List<IndexRec> list;
    SharedPreferences role;

    public IndexGadapter(Context context, List<IndexRec> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.role = context.getSharedPreferences("role", Context.MODE_APPEND);
        role.edit().commit();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView xianjia;
        public TextView yuanjia;
        public TextView kucun;
        public ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            xianjia = (TextView) itemView.findViewById(R.id.xianjia);
            yuanjia = (TextView) itemView.findViewById(R.id.yuanjia);
            kucun = (TextView) itemView.findViewById(R.id.kucun);
            img = (ImageView) itemView.findViewById(R.id.img);
//            yuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.indexgitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {

                    listener.onItemClick(v, (Integer) v.getTag());

                }

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImgUtils.getimgutils().GetImg(context, list.get(position).getGoods_image(), holder.img);
        holder.title.setText(list.get(position).getGoods_name());
        holder.kucun.setText("库存:" + list.get(position).getGoods_storage());
        if (role.getString("role", "").equals("0")) {
            holder.xianjia.setText("税前价 ¥ " +list.get(position).getGoods_price() );
            holder.yuanjia.setVisibility(View.GONE);
            holder.xianjia.setVisibility(View.VISIBLE);
        } else if (role.getString("role", "").equals("1")) {
            holder.xianjia.setVisibility(View.GONE);
            holder.yuanjia.setVisibility(View.VISIBLE);
            holder.yuanjia.setText("税后价 ¥ " + list.get(position).getGoods_price_tax());
        } else if (role.getString("role", "").equals("2")) {
            holder.xianjia.setText("税前价 ¥ " +list.get(position).getGoods_price() );
            holder.yuanjia.setText("税后价 ¥ " + list.get(position).getGoods_price_tax());
            holder.xianjia.setVisibility(View.VISIBLE);
            holder.yuanjia.setVisibility(View.VISIBLE);
        }


        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    OnItemClickListener listener = null;

    //模仿listview创建一个借口，然后将itemclick暴露出去，典型的观察着模式
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;

    }


}
