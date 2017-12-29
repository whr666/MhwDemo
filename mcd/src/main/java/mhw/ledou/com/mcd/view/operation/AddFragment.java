package mhw.ledou.com.mcd.view.operation;


import android.app.Fragment;


import java.util.ArrayList;

import mhw.ledou.com.mcd.fragment.ClassfiyView;
import mhw.ledou.com.mcd.fragment.IndexView;
import mhw.ledou.com.mcd.fragment.MeView;
import mhw.ledou.com.mcd.fragment.ShopcartView;

/**
 * Created by XIAOXIN on 2017/7/7.
 */

public class AddFragment {


    public static ArrayList<Fragment> HomeFragment(){
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new IndexView());
        fragments.add(new ClassfiyView());
        fragments.add(new ShopcartView());
        fragments.add(new MeView());
        return fragments;
    }


}
