package mhw.ledou.com.mcd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import mhw.ledou.com.mcd.model.TypeFragment;
import mhw.ledou.com.mcd.presente.FragmentController;
import mhw.ledou.com.mcd.status.Status;
import mhw.ledou.com.mcd.view.operation.AddFragment;
import mhw.ledou.com.mcd.view.operation.BindView;
import mhw.ledou.com.mcd.view.operation.NoteView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class HomeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    public static HomeActivity activity;
    public FragmentManager fragmentManager;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment currentFragment = new Fragment();
    FragmentController fc = new FragmentController();
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    public SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        NoteView.inject(this);
        Status.setstatus(this);
        user = getSharedPreferences("user", Context.MODE_APPEND);
        user.edit().commit();

        settabbar();
//        fragmentManager = getSupportFragmentManager(); //兼容3.0以下
        fragmentManager = getFragmentManager(); //3.0以上可以这么写
        fc.fragmentFC(//fragment添加
                this,
                savedInstanceState,
                fragmentManager,0,
                AddFragment.HomeFragment()
        );
    }

    @Override
    public void onTabSelected(int position) {
//选中
        if (AddFragment.HomeFragment() != null) {
            switch (position) {
                case 0:
                    TypeFragment.currentIndex = 0;
                    break;
                case 1:
                    TypeFragment.currentIndex = 1;
                    break;
                case 2:
                    TypeFragment.currentIndex = 2;
                    break;
                case 3:
                    TypeFragment.currentIndex = 3;
                    break;
            }
            fc.showFragment();
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }




    public  void  settabbar(){
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //标注
        TextBadgeItem numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(1)//消息圆圈边框
                .setBackgroundColor(Color.RED)
                .setText("5")//消息数量
                .setHideOnSelect(true);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.home, "首页")
                        .setActiveColorResource(R.color.tabbartx)
                        .setBadgeItem(null))//标注

                .addItem(new BottomNavigationItem(R.mipmap.classify, "分类")
                        .setActiveColorResource(R.color.tabbartx)
                        .setBadgeItem(null))

                .addItem(new BottomNavigationItem(R.mipmap.shopping, "购物车")
                        .setActiveColorResource(R.color.tabbartx)
                        .setBadgeItem(null))

                .addItem(new BottomNavigationItem(R.mipmap.personal, "我的")
                        .setActiveColorResource(R.color.tabbartx)
                        .setBadgeItem(null))

                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);




    }




}
