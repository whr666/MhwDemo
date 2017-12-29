package mhw.ledou.com.mcd.activity.base;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/18.
 */

public class Infomodel {


//    "gid":57,
//            "goods_image":"http://caigou.mcdsh.com/uploads/goods/1480473690_thumb.jpg",
//            "goods_brand":"古驰",
//            "goods_name":"古奇（Gucci）女士 牛皮互扣式双G腰带 370543 AP00G 4134 ",
//            "goods_price":"2205.13",
//            "goods_price_tax":"2580.00",
//            "goods_storage":4,
//            "goods_spec":Array[3],
//            "goods_attr":Object{...},
//            "goods_body"

    String gid;
    String goods_image;
    String goods_brand;
    String goods_name;
    String goods_price;
    String goods_price_tax;
    String goods_storage;
    List<Skus> goods_spec;
    Object goods_attr;
    String goods_body;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Object getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(Object goods_attr) {
        this.goods_attr = goods_attr;
    }

    public String getGoods_body() {
        return goods_body;
    }

    public void setGoods_body(String goods_body) {
        this.goods_body = goods_body;
    }

    public String getGoods_brand() {
        return goods_brand;
    }

    public void setGoods_brand(String goods_brand) {
        this.goods_brand = goods_brand;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_price_tax() {
        return goods_price_tax;
    }

    public void setGoods_price_tax(String goods_price_tax) {
        this.goods_price_tax = goods_price_tax;
    }

    public List<Skus> getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(List<Skus> goods_spec) {
        this.goods_spec = goods_spec;
    }

    public String getGoods_storage() {
        return goods_storage;
    }

    public void setGoods_storage(String goods_storage) {
        this.goods_storage = goods_storage;
    }
}
