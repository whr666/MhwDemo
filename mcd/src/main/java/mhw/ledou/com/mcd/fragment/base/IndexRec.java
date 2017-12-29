package mhw.ledou.com.mcd.fragment.base;

/**
 * Created by XIAOXIN on 2017/10/17.
 */

public class IndexRec {
//    "gid":"192",
//            "goods_name":"男款时尚潮流长袖夹克外套 双色领口拼接 黑色 ",
//            "goods_image":"http://caigou.mcdsh.com/uploads/goods/1487556632_thumb.jpg",
//            "goods_storage":"2",
//            "goods_price":"453.85",
//            "goods_price_tax":"531.00"
    String gid;
    String goods_name;
    String goods_image;
    String goods_storage;
    String goods_price;
    String goods_price_tax;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
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

    public String getGoods_storage() {
        return goods_storage;
    }

    public void setGoods_storage(String goods_storage) {
        this.goods_storage = goods_storage;
    }
}
