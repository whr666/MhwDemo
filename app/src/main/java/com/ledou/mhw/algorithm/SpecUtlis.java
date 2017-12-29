package com.ledou.mhw.algorithm;

import com.ledou.mhw.activity.base.AsseSkus;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XIAOXIN on 2017/10/10.
 */

public class SpecUtlis {

    private static List<AsseSkus> list;
    private static String spec1id;

    public static List<AsseSkus> getAsseSkus(List<AsseSkus> list, String spec1id){
        List<AsseSkus> asselist = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_id_1().equals(spec1id)){
                asselist.add(list.get(i));
            }
        }
        return asselist;
    }

    public static AsseSkus getsku(List<AsseSkus> list,String spec1id){
        AsseSkus pic = null;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_id_1().equals(spec1id)){
                pic = list.get(i);
            }
        }
        return pic;
    }


    public static String getpic(List<AsseSkus> list,String spec1id){
        String pic = null;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_id_1().equals(spec1id)){
                pic = list.get(i).getSku_pic();
            }
        }
        return pic;
    }
    public static String getmpic(List<AsseSkus> list,String spec1id){
        String pic = null;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_id_1().equals(spec1id)){
                pic = list.get(i).getSku_market_price();
            }
        }
        return pic;
    }




//选择颜色时的id
    public static String getskuid(List<AsseSkus> list,String string){
        String pic = null;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_value_1().equals(string)  || list.get(i).getSku_spec_value_2().equals(string) ){
                pic = list.get(i).getId();
            }
        }
        return pic;
    }

    //选择颜色时的id
    public static String getskuid(List<AsseSkus> list,String colorid,String sizeid){
        String pic = null;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getSku_spec_value_1().equals(colorid) && list.get(i).getSku_spec_value_2().equals(sizeid)){
                pic = list.get(i).getId();
            }
        }
        return pic;
    }





}
