package com.ledou.mhw.fragment.base;

/**
 * Created by XIAOXIN on 2017/10/13.
 */

public class Child {

////      "cart_id":1,
//    "sku_id":1,
//            "sku_name":"棕色",
//            "sku_price":"4999.00",
//            "cart_num":9,
//            "sku_stock":100
    /** 是否是编辑状态 */
    private boolean isEditing;
    /** 是否被选中 */
    private boolean isChildSelected;

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    String cart_id;
    String sku_id;
    String sku_name;
    String sku_price;
    String cart_num;
    String sku_stock;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_num() {
        return cart_num;
    }

    public void setCart_num(String cart_num) {
        this.cart_num = cart_num;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getSku_stock() {
        return sku_stock;
    }

    public void setSku_stock(String sku_stock) {
        this.sku_stock = sku_stock;
    }
}




