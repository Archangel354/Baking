package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.R;
import com.example.baking.models.BakingModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


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
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(dContext).inflate(R.layout.ingredients_steps_item, parent, false);
        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int position) {
        BakingModel currentItem = mExampleList.get(position);

        String ingredients = currentItem.getIngredientsList();
        List steps = currentItem.getStepsList();

        detailViewHolder.dTextIngredients.setText(ingredients);
        //detailViewHolder.dTextStep.List(steps);




    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        public TextView dTextIngredients;
        public TextView dTextStep;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            dTextIngredients = itemView.findViewById(R.id.txtIngredients);
            //dTextStep = itemView.findViewById(R.id.rvSteps);
        }
    }
}
