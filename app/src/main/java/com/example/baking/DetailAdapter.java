package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.R;
import com.example.baking.models.BakingModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private Context dContext;
    private ArrayList<BakingModel> mExampleList;
   // private OnItemClickListener mListener;


    public DetailAdapter(Context context, ArrayList<BakingModel> exampleList) {
        dContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        public TextView dTextIngredients;
        public TextView dTextStep;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            dTextIngredients = itemView.findViewById(R.id.txtIngredients);
            dTextStep = itemView.findViewById(R.id.rvSteps);
        }
    }
}
