package com.ledou.mhw.activity.base;

/**
 * Created by XIAOXIN on 2017/10/10.
 */

public class AsseSkus {

//    "id": 3,
//            "sku_spec_id_1": 3,
//            "sku_spec_id_2": 6,
//            "sku_spec_id_group": "3,6",
//            "sku_spec_type_1": "颜色分类",
//            "sku_spec_value_1": "黑色",
//            "sku_spec_type_2": "尺码",
//            "sku_spec_value_2": "36",
//            "sku_pic": "http://meihe.cc/storage/images/wh8yUgqQfXYZJzupVGgIPjr49x99dIwngt5aFb18_md.jpeg",
//            "sku_sn": null,
//            "sku_price": "1690.00",
//            "sku_market_price": "1880.00",
//            "sku_stock": 100

    String id;
    String sku_spec_id_1;
    String sku_spec_id_2;
    String sku_spec_id_group;
    String sku_spec_type_1;
    String sku_spec_value_1;
    String sku_spec_type_2;
    String sku_spec_value_2;
    String sku_pic;
    String sku_sn;
    String sku_price;
    String sku_market_price;
    String sku_stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku_market_price() {
        return sku_market_price;
    }

    public void setSku_market_price(String sku_market_price) {
        this.sku_market_price = sku_market_price;
    }

    public String getSku_pic() {
        return sku_pic;
    }

    public void setSku_pic(String sku_pic) {
        this.sku_pic = sku_pic;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getSku_sn() {
        return sku_sn;
    }

    public void setSku_sn(String sku_sn) {
        this.sku_sn = sku_sn;
    }

    public String getSku_spec_id_1() {
        return sku_spec_id_1;
    }

    public void setSku_spec_id_1(String sku_spec_id_1) {
        this.sku_spec_id_1 = sku_spec_id_1;
    }

    public String getSku_spec_id_2() {
        return sku_spec_id_2;
    }

    public void setSku_spec_id_2(String sku_spec_id_2) {
        this.sku_spec_id_2 = sku_spec_id_2;
    }

    public String getSku_spec_id_group() {
        return sku_spec_id_group;
    }

    public void setSku_spec_id_group(String sku_spec_id_group) {
        this.sku_spec_id_group = sku_spec_id_group;
    }

    public String getSku_spec_type_1() {
        return sku_spec_type_1;
    }

    public void setSku_spec_type_1(String sku_spec_type_1) {
        this.sku_spec_type_1 = sku_spec_type_1;
    }

    public String getSku_spec_type_2() {
        return sku_spec_type_2;
    }

    public void setSku_spec_type_2(String sku_spec_type_2) {
        this.sku_spec_type_2 = sku_spec_type_2;
    }

    public String getSku_spec_value_1() {
        return sku_spec_value_1;
    }

    public void setSku_spec_value_1(String sku_spec_value_1) {
        this.sku_spec_value_1 = sku_spec_value_1;
    }

    public String getSku_spec_value_2() {
        return sku_spec_value_2;
    }

    public void setSku_spec_value_2(String sku_spec_value_2) {
        this.sku_spec_value_2 = sku_spec_value_2;
    }

    public String getSku_stock() {
        return sku_stock;
    }

    public void setSku_stock(String sku_stock) {
        this.sku_stock = sku_stock;
    }

    @Override
    public String toString() {
        return "AsseSkus{" +
                "id='" + id + '\'' +
                ", sku_spec_id_1='" + sku_spec_id_1 + '\'' +
                ", sku_spec_id_2='" + sku_spec_id_2 + '\'' +
                ", sku_spec_id_group='" + sku_spec_id_group + '\'' +
                ", sku_spec_type_1='" + sku_spec_type_1 + '\'' +
                ", sku_spec_value_1='" + sku_spec_value_1 + '\'' +
                ", sku_spec_type_2='" + sku_spec_type_2 + '\'' +
                ", sku_spec_value_2='" + sku_spec_value_2 + '\'' +
                ", sku_pic='" + sku_pic + '\'' +
                ", sku_sn='" + sku_sn + '\'' +
                ", sku_price='" + sku_price + '\'' +
                ", sku_market_price='" + sku_market_price + '\'' +
                ", sku_stock='" + sku_stock + '\'' +
                '}';
    }
}
