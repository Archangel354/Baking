package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.models.BakingModel;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private Context dContext;
    private ArrayList<BakingModel.Steps> mStepList;
    //private RecyclerView dRecyclerView;
    // private OnItemClickListener mListener;


    public DetailAdapter(Context context, ArrayList<BakingModel.Steps> stepList) {
        dContext = context;
        mStepList = stepList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(dContext).inflate(R.layout.ingredients_steps_item, parent, false);
        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int position) {
        BakingModel.Steps currentItem = mStepList.get(position);

        String ingredients = currentItem.getShortDescription();
        //List steps = currentItem.getStepsList();

        //detailViewHolder.dTextStep.setText(ingredients);
        //detailViewHolder.dTextStep.List(steps);
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        //public TextView dTextIngredients;
        public TextView dTextStep;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            //dTextIngredients = itemView.findViewById(R.id.txtIngredients);
            dTextStep = itemView.findViewById(R.id.txtStep);
        }
    }
}
