package com.xiong.sampler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickLitener {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerAdapter adapter = new RecyclerAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
        //设置布局模式
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //点击监听和长按监听
        adapter.setOnItemClickLitener(this);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            mDatas.add("" + i);
        }

    }

    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this, "点击了条目"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this, "长按删除"+position, Toast.LENGTH_SHORT).show();
    }
}
