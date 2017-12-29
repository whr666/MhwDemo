package mhw.ledou.com.mcd;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

import mhw.ledou.com.mcd.status.Status;

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
