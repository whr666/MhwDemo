package com.ledou.mhw.fragment.base;

import com.ledou.mhw.fragment.base.Group;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/16.
 */

public class Cart {


//    "store_id":1000,
//            "store_name":"自营",
//            "cart_goods":


    String store_id;
    String store_name;
    List<Cart2> cart_goods;

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public List<Cart2> getCart_goods() {
        return cart_goods;
    }

    public void setCart_goods(List<Cart2> cart_goods) {
        this.cart_goods = cart_goods;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
