package com.example.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Adapter for the gridview of movies from the JSON data */
    private RecipeAdapter rAdapter;
    public final static String RECIPESTRING = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private RecyclerView rRecyclerView;
    private ArrayList<RecipeList> rRecipeList;
    private RequestQueue rRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rRecyclerView =  findViewById(R.id.recycler_view);

        // Create a new adapter that takes an empty list of movies as input
        rAdapter = new RecipeAdapter(MainActivity.this, rRecipeList);
        rRecyclerView.setHasFixedSize(true);
        // Set the adapter on the {@link GridView} so the list can be populated in the user interface
        rRecyclerView.setAdapter(rAdapter);
        rRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
