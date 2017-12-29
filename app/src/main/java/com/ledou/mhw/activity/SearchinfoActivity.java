package com.ledou.mhw.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.ledou.mhw.R;
import com.ledou.mhw.fragment.IndexView;
import com.ledou.mhw.fragment.adapter.IndexGadapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class SearchinfoActivity extends AppCompatActivity {
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchinfo);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
//        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setGridLayout(2);//参数为列数
        IndexGadapter adapter1 =   new IndexGadapter(LayoutInflater.from(this),this, IndexView.mview.indexHs);
        mPullLoadMoreRecyclerView.setAdapter(adapter1);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }
}
