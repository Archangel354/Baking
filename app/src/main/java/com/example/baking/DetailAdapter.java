package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter> {
    private Context dContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;



    @NonNull
    @Override
    public DetailAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter detailAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
