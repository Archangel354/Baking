package com.example.baking;

import android.content.Context;


public class StepDetailAdapter {

    private Context rContext;
    private OnItemClickListener sListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }
}
