package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context rContext;
    private ArrayList<RecipeList> rRecipeList;

    public RecipeAdapter(Context context, ArrayList<RecipeList> recipeList) {
        rContext = context;
        rRecipeList = recipeList;
    }


    @Override
    public RecipeViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View v = LayoutInflater.from(rContext).inflate(R.layout.recipe, parent,false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return rRecipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView rName;
        public TextView rServings;
        public TextView rImage;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            rName = itemView.findViewById(R.id.txtRecipeName);
            rServings = itemView.findViewById(R.id.txtServings);
            rImage = itemView.findViewById(R.id.txtImage);
        }
    }


}
