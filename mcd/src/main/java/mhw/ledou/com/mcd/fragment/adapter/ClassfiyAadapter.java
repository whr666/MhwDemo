package mhw.ledou.com.mcd.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import mhw.ledou.com.mcd.R;
import mhw.ledou.com.mcd.fragment.base.ClassL;

/**
 * Created by XIAOXIN on 2017/9/11.
 */

public class ClassfiyAadapter extends RecyclerView.Adapter<ClassfiyAadapter.ViewHolder>{

      Context context;
      List<ClassL> list;
      int index = 0;

    public ClassfiyAadapter(Context context,List<ClassL> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 获取view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classleft, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });

            return viewHolder;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素，获取view中的控件
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView classtext;
        public ViewHolder(View view){
            super(view);
            classtext = (TextView) view.findViewById(R.id.classtext);
        }
    }

    public void setbg(int pos){
        this.index = pos;
    }
    /**
     * 赋值
     * @param holder
     * @param position
     */
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.classtext.setText(list.get(position).getGc_name()+"");
        holder.itemView.setTag(position);

        if(index == position){
            holder.classtext.setBackgroundResource(R.drawable.classedge);
        }else{
            holder.classtext.setBackgroundResource(R.drawable.unclassedge);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    OnItemClickListener listener = null;

    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnitemcliklistener(OnItemClickListener listener){

        this.listener = listener;

    }


}
