package com.ledou.mhw.fragment.base;

import java.util.ArrayList;

/**
 * Created by XIAOXIN on 2017/9/11.
 */

public class Aclass  {
//
//    "id":1,
//    "class_name":"箱包配饰",
//    "class_pic":null

    @Override
    public String toString() {
        return "Aclass{" +
                "class_name='" + class_name + '\'' +
                ", id='" + id + '\'' +
                ", class_pic='" + class_pic + '\'' +
                '}';
    }

    String id;
    String class_name;
    String class_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_pic() {
        return class_pic;
    }

    public void setClass_pic(String class_pic) {
        this.class_pic = class_pic;
    }
}
