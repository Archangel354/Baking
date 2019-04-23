package com.example.baking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context rContext;
    public static List<RecipeList> rRecipeList;
    private static final String TAG = "RecipeAdapter";
    private OnItemClickListener rListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        rListener = listener;
    }



    public RecipeAdapter(Context Context, ArrayList<RecipeList> recipelist) {
        rRecipeList = recipelist;
        rContext = Context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
