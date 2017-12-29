package com.ledou.mhw.view.operation;



import android.app.Fragment;

import com.ledou.mhw.fragment.IndexView;
import com.ledou.mhw.fragment.ClassfiyView;
import com.ledou.mhw.fragment.ShopcartView;
import com.ledou.mhw.fragment.MeView;

import java.util.ArrayList;

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
