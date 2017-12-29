package com.ledou.mhw.activity.base;

/**
 * Created by XIAOXIN on 2017/10/9.
 */

public class Spec1 {


//    "id":1,
//            "sku_spec_type_id":100,
//            "sku_spec_type":"颜色分类",
//            "sku_spec_value":"棕色",
//            "sku_spec_sn":null,
//            "sku_spec_pic":"http://mei
    String id;
    String sku_spec_type_id;
    String sku_spec_type;
    String sku_spec_value;
    String sku_spec_sn;
    String sku_spec_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku_spec_pic() {
        return sku_spec_pic;
    }

    public void setSku_spec_pic(String sku_spec_pic) {
        this.sku_spec_pic = sku_spec_pic;
    }

    public String getSku_spec_sn() {
        return sku_spec_sn;
    }

    public void setSku_spec_sn(String sku_spec_sn) {
        this.sku_spec_sn = sku_spec_sn;
    }

    public String getSku_spec_type() {
        return sku_spec_type;
    }

    public void setSku_spec_type(String sku_spec_type) {
        this.sku_spec_type = sku_spec_type;
    }

    public String getSku_spec_type_id() {
        return sku_spec_type_id;
    }

    public void setSku_spec_type_id(String sku_spec_type_id) {
        this.sku_spec_type_id = sku_spec_type_id;
    }

    public String getSku_spec_value() {
        return sku_spec_value;
    }

    public void setSku_spec_value(String sku_spec_value) {
        this.sku_spec_value = sku_spec_value;
    }
}
