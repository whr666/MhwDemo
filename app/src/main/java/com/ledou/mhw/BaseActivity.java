package com.ledou.mhw;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ledou.mhw.status.Status;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by XIAOXIN on 2017/7/7
 */

public class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Status.setstatus(this);//沉侵状态栏



    }
}
