package com.xiong.sampler;
/*
 *  @项目名：  sampler 
 *  @包名：    com.xiong.sampler
 *  @文件名:   RecyclerAdapter
 *  @创建者:   ${huanglibo}
 *  @创建时间: 2016/10/30 15:29
 */


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    List<Integer> mHeightList = new ArrayList<>();
    Random mRandom = new Random();
    List<String> mDatas;
    public RecyclerAdapter(List<String> datas) {
        mDatas = datas;

        for (int i = 0; i <mDatas.size() ; i++) {
            mHeightList.add( (int) (50 + Math.random() * 200));
        }

    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        mHeightList.add( (int) (50 + Math.random() * 200));
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    /**
     * 定义条目控制器
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        MyviewHolder myviewHolder = new MyviewHolder(View.inflate(viewGroup.getContext(),R.layout.item,null));

        return myviewHolder;
    }

    /**
     * 设置属性
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyviewHolder viewHolder, int position) {

        viewHolder.mTextView.setText(mDatas.get(position));
        //设置随机高度
        ViewGroup.LayoutParams params = viewHolder.mTextView.getLayoutParams();
        int height = mHeightList.get(position);
        params.height = height;
        viewHolder.mTextView.setLayoutParams(params);
        //设置随机颜色
        GradientDrawable gradientDrawable = new GradientDrawable();
        int a = 255;
        int r = 100 + mRandom.nextInt(150);
        int g = 100 + mRandom.nextInt(150);
        int b = 100 + mRandom.nextInt(150);
        gradientDrawable.setColor(Color.argb(a, r, g, b));

        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(Color.GRAY);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(new int[]{}, gradientDrawable);

        viewHolder.mTextView.setBackground(stateListDrawable);

        //条目的点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, pos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(viewHolder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }

    }

    /**
     * 条目数目
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 和条目对应的控制器
     */
    class MyviewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public MyviewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.info_text);
        }
    }

    /**
     * 为点击事件准备的接口
     */
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        mOnItemClickLitener = onItemClickLitener;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }



}
