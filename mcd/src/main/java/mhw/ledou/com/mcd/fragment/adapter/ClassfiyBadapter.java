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
import mhw.ledou.com.mcd.fragment.base.ClassR;
import mhw.ledou.com.mcd.getimg.ImgUtils;

/**
 * Created by XIAOXIN on 2017/9/11.
 */

public class ClassfiyBadapter extends RecyclerView.Adapter<ClassfiyBadapter.ViewHolder>{

    Context context;
    List<ClassR> list;
    int index = 0;

    public ClassfiyBadapter(Context context,List<ClassR> list) {
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
    public ClassfiyBadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calssright, parent, false);
        ClassfiyBadapter.ViewHolder viewHolder = new ClassfiyBadapter.ViewHolder(view);
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
        public TextView classright;
        public ImageView right_img;
        public ViewHolder(View view){
            super(view);
            classright = (TextView) view.findViewById(R.id.classright);
            right_img = (ImageView) view.findViewById(R.id.right_img);
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
    public void onBindViewHolder(ClassfiyBadapter.ViewHolder holder, int position) {
        holder.classright.setText(list.get(position).getGb_name()+"");
        ImgUtils.getimgutils().GetImg(context,list.get(position).getGb_pic(),holder.right_img);
        holder.itemView.setTag(position);
//        if(index == position){
//            holder.classright.setBackgroundResource(R.drawable.unilabut);
//        }else{
//            holder.classright.setBackgroundResource(R.drawable.unclassedge);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    ClassfiyAadapter.OnItemClickListener listener = null;

    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnitemcliklistener(ClassfiyAadapter.OnItemClickListener listener){

        this.listener = listener;

    }


}