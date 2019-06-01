package com.example.baking;

import android.content.Context;


public class StepDetailAdapter {

    private Context rContext;
    private OnItemClickListener sListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(StepDetailAdapter.OnItemClickListener listener) {
        sListener = listener;
    }

    public StepDetailAdapter(Context rContext, OnItemClickListener sListener) {
        this.rContext = rContext;
        this.sListener = sListener;
    }
}
