package mhw.ledou.com.mcd.algorithm;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mhw.ledou.com.mcd.activity.base.Skus;
import mhw.ledou.com.mcd.activity.base.Spec1;
import mhw.ledou.com.mcd.activity.base.Spec2;

/**
 * Created by XIAOXIN on 2017/10/10.
 */

public class SpecUtlis {

    public static List<Spec1> getColorList(List<Skus> list) {
        List<Spec1> spec1s = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
                spec1s.add(new Spec1(list.get(i).getColor()));

        }

        for (int i = 0; i <spec1s.size() ; i++) {
            for ( int  j  =  spec1s.size()  -   1 ; j  >  i; j --) {
                if  (spec1s.get(j).getColor().equals(spec1s.get(i).getColor()))  {
                    spec1s.remove(j);
                }
            }


        }


        return spec1s;
    }

    public static List<Spec2> getSizeList(List<Skus> list) {
        List<Spec2> spec1s = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
                spec1s.add(new Spec2(list.get(i).getSize()));
        }


        for (int i = 0; i <spec1s.size() ; i++) {
            for (int j = spec1s.size() - 1; j > i; j--) {
                if (spec1s.get(j).getSize().equals(spec1s.get(i).getSize())) {
                    spec1s.remove(j);
                }
            }
        }
        return spec1s;
    }

    public static List<Spec2> getSizeList(List<Skus> list,String color) {
        List<Spec2> spec1s = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (color.equals(list.get(i).getColor())){
                spec1s.add(new Spec2(list.get(i).getSize()));
            }
        }

        for (int i = 0; i <spec1s.size() ; i++) {
            for (int j = spec1s.size() - 1; j > i; j--) {
                if (spec1s.get(j).getSize().equals(spec1s.get(i).getSize())) {
                    spec1s.remove(j);
                }
            }
        }
        return spec1s;
    }

    /**
     * skuid
     * @param list
     * @param color
     * @param size
     * @return
     */

    public static String getSkuid(List<Skus> list,String color,String size) {
        String skuid = null;
        for (int i = 0; i < list.size(); i++) {

            if (color.equals(list.get(i).getColor()) && size.equals(list.get(i).getSize())) {
                skuid = list.get(i).getId();
            }

        }

        return skuid;
    }

    /**
     * skuid
     * @param list
     * @return
     */
    public static String getSkuid(List<Skus> list,String spec) {
        String skuid = null;
        for (int i = 0; i < list.size(); i++) {
            if (spec.equals(list.get(i).getColor())|| spec.equals(list.get(i).getSize())) {
                skuid = list.get(i).getId();
            }
        }
        return skuid;
    }

}
