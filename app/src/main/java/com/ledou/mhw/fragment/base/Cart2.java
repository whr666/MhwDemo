package com.ledou.mhw.fragment.base;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/16.
 */

public class Cart2 {


    /** 是否处于编辑状态 */
    private boolean isEditing;
    /** 组是否被选中 */
    private boolean isGroupSelected;
    private boolean isAllGoodsInvalid;

    public boolean isAllGoodsInvalid() {
        return isAllGoodsInvalid;
    }

    public void setAllGoodsInvalid(boolean allGoodsInvalid) {
        isAllGoodsInvalid = allGoodsInvalid;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isGroupSelected() {
        return isGroupSelected;
    }

    public void setGroupSelected(boolean groupSelected) {
        isGroupSelected = groupSelected;
    }

    //     "goods_info":Object{...},
//    "goods_skus"


    Group goods_info;
    List<Child> goods_skus;

    public Group getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(Group goods_info) {
        this.goods_info = goods_info;
    }

    public List<Child> getGoods_skus() {
        return goods_skus;
    }

    public void setGoods_skus(List<Child> goods_skus) {
        this.goods_skus = goods_skus;
    }
}
