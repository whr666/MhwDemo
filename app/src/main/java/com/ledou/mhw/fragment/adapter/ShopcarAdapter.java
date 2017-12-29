package com.ledou.mhw.fragment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ledou.mhw.R;
import com.ledou.mhw.algorithm.ShoppingCartBiz;
import com.ledou.mhw.fragment.base.Cart2;
import com.ledou.mhw.fragment.base.Child;
import com.ledou.mhw.fragment.base.Group;
import com.ledou.mhw.getimg.ImgUtils;
import com.ledou.mhw.view.widget.TostUtlis;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/13.
 */

public class ShopcarAdapter extends BaseExpandableListAdapter{
    Context context;
    List<Cart2> list;
    LayoutInflater inflater;
    TostUtlis tostUtlis;
    public interface OnShoppingCartChangeListener {
        void onDataChange(String selectCount, String selectMoney);
        void onSelectItem(boolean isSelectedAll);
    }

    private OnShoppingCartChangeListener mChangeListener;
    private boolean isSelectAll = false;

    public void setOnShoppingCartChangeListener(OnShoppingCartChangeListener changeListener) {
        this.mChangeListener = changeListener;
    }
    public View.OnClickListener getAdapterListener() {
        return listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                //购物车全选
                case R.id.shopall:
                    isSelectAll = ShoppingCartBiz.selectAll(list, isSelectAll, (ImageView) v);
                    setSettleInfo();
                    notifyDataSetChanged();
                    break;
                case R.id.jiesuan:
                    if (ShoppingCartBiz.hasSelectedGoods(list)) {



                        tostUtlis.setString("结算跳转");
                    } else {
                        tostUtlis.setString("亲，先选择商品！");
                    }
                    //group
                    break;
                case R.id.group_select:
                    int groupPosition3 = Integer.parseInt(String.valueOf(v.getTag()));
                    isSelectAll = ShoppingCartBiz.selectGroup(list, groupPosition3);
                    selectAll();
                    setSettleInfo();
                    notifyDataSetChanged();
                    break;
                //child
                case R.id.child_select:
                    String tag = String.valueOf(v.getTag());
//                    把父类也加上，就算没有也不要紧吧？
                    if (tag.contains(",")) {
                        String s[] = tag.split(",");
                        int groupPosition = Integer.parseInt(s[0]);
                        int childPosition = Integer.parseInt(s[1]);
                        isSelectAll = ShoppingCartBiz.selectOne(list, groupPosition, childPosition);
                        selectAll();
                        setSettleInfo();
                        notifyDataSetChanged();
                    }
                    break;
//                case R.id.tvDel:
//                    String tagPos = String.valueOf(v.getTag());
//                    if (tagPos.contains(",")) {
//                        String s[] = tagPos.split(",");
//                        int groupPosition = Integer.parseInt(s[0]);
//                        int childPosition = Integer.parseInt(s[1]);
//                        showDelDialog(groupPosition, childPosition);
//                    }
//                    break;
                case R.id.jia:
                    tostUtlis.setString("加");
//                    ShoppingCartBiz.addOrReduceGoodsNum(true, (ShoppingCartBean.Goods) v.getTag(), ((TextView) (((View) (v.getParent())).findViewById(R.id.tvNum2))));
//                    setSettleInfo();
                    break;
                case R.id.jian:
                    tostUtlis.setString("减");
//                    ShoppingCartBiz.addOrReduceGoodsNum(false, (ShoppingCartBean.Goods) v.getTag(), ((TextView) (((View) (v.getParent())).findViewById(R.id.tvNum2))));
//                    setSettleInfo();
                    break;
            }
        }
    };

    private void selectAll() {
        if (mChangeListener != null) {
            mChangeListener.onSelectItem(isSelectAll);
        }
    }

    private void setSettleInfo() {
        String[] infos = ShoppingCartBiz.getShoppingCount(list);
        //删除或者选择商品之后，需要通知结算按钮，更新自己的数据；
        if (mChangeListener != null && infos != null) {
            mChangeListener.onDataChange(infos[0], infos[1]);
        }
    }








    public ShopcarAdapter(Context context,List<Cart2> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        tostUtlis = new TostUtlis(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getGoods_skus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getGoods_skus().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHold hold;
        Group group = list.get(groupPosition).getGoods_info();
        if (convertView == null) {
            hold = new GroupViewHold();
            convertView = inflater.inflate(R.layout.shopcargroupitem, parent, false);
            hold.group_select = (ImageView) convertView.findViewById(R.id.group_select);
            hold.group_img = (ImageView) convertView.findViewById(R.id.group_img);
            hold.group_name = (TextView) convertView.findViewById(R.id.group_name);
            hold.group_pic = (TextView) convertView.findViewById(R.id.group_pic);
            hold.group_num = (TextView) convertView.findViewById(R.id.group_num);
            hold.group_view = convertView.findViewById(R.id.group_view);
            convertView.setTag(hold);
        } else {
            hold = (GroupViewHold) convertView.getTag();
        }
        ShoppingCartBiz.checkItem(list.get(groupPosition).isGroupSelected(),hold.group_select);
        boolean isEditing = list.get(groupPosition).isEditing();
        if (groupPosition==0){
            hold.group_view.setVisibility(View.GONE);
        }else{
            hold.group_view.setVisibility(View.VISIBLE);
        }
        ImgUtils.getimgutils().GetImg(context, group.getGoods_pic(), hold.group_img);
        hold.group_name.setText(group.getGoods_name());
        hold.group_pic.setText(group.getGoods_price());
        hold.group_num.setText(group.getGoods_num());

        hold.group_select.setTag(groupPosition);
        hold.group_select.setOnClickListener(listener);
        return convertView;
    }

    class GroupViewHold {
        ImageView group_select;
        ImageView group_img;
        TextView group_name;
        TextView group_pic;
        TextView group_num;
        View group_view;
    }

    class ChildViewHold {
        ImageView child_select;
        ImageView child_add;
        ImageView child_redu;
        TextView child_spec;
        TextView child_pic;
        EditText child_num;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHold hold;
        Child child = list.get(groupPosition).getGoods_skus().get(childPosition);
        if (convertView == null) {
            hold = new ChildViewHold();
            convertView = inflater.inflate(R.layout.shopcarchilditem, parent, false);
            hold.child_select = (ImageView) convertView.findViewById(R.id.child_select);
            hold.child_add = (ImageView) convertView.findViewById(R.id.child_add);
            hold.child_redu = (ImageView) convertView.findViewById(R.id.child_redu);
            hold.child_spec = (TextView) convertView.findViewById(R.id.child_spec);
            hold.child_pic = (TextView) convertView.findViewById(R.id.child_pic);
            hold.child_num = (EditText) convertView.findViewById(R.id.child_num);
            convertView.setTag(hold);
        } else {
            hold = (ChildViewHold) convertView.getTag();
        }
        boolean isChildSelected = list.get(groupPosition).getGoods_skus().get(childPosition).isChildSelected();
        boolean isEditing = child.isEditing();
        hold.child_select.setTag(groupPosition + "," + childPosition);
        hold.child_spec.setText(child.getSku_name());
        hold.child_pic.setText("¥ "+child.getSku_price()+"/件");
        hold.child_num.setText(child.getCart_num());


        hold.child_add.setTag(child);
        hold.child_redu.setTag(child);

        ShoppingCartBiz.checkItem(isChildSelected,hold.child_select);
        hold.child_select.setOnClickListener(listener);
        hold.child_add.setOnClickListener(listener);
        hold.child_redu.setOnClickListener(listener);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
