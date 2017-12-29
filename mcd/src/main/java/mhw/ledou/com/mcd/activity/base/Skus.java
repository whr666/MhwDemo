package mhw.ledou.com.mcd.activity.base;

/**
 * Created by XIAOXIN on 2017/10/12.
 */

public class Skus {
//    "color":"蓝色",
//            "size":"90",
//            "storage":"1",
//            "goods_serial":""

    String id;
    String color;
    String size;
    String storage;
    String goods_serial;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGoods_serial() {
        return goods_serial;
    }

    public void setGoods_serial(String goods_serial) {
        this.goods_serial = goods_serial;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
