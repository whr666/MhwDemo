package com.ledou.mhw.fragment.base;

import java.util.List;

/**
 * Created by XIAOXIN on 2017/9/14.
 */

public class IndexH {

//    "pic":"http://meihe.cc/storage/banners/1.jpg",
//            "link":"goods/1",
//            "goods":[
//    {
//        "id":1,
//            "goods_name":"[测试商品] mcm 女包 小号 双肩包 MMK6SVE19",
//            "goods_pic":"http://meihe.cc/storage/images/5XB1ipl22nWQcHOO4TEpilDK2fR9YcA8a3AEhnLC_sm.jpeg",
//            "goods_price":"6999.00",
//            "goods_market_price":"6999.00"
//    },

    String pic;
    String link;
    List<Goods> goods;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public class Goods{

        String id;
        String goods_name;
        String goods_pic;
        String goods_price;
        String goods_market_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_market_price() {
            return goods_market_price;
        }

        public void setGoods_market_price(String goods_market_price) {
            this.goods_market_price = goods_market_price;
        }
    }





}
