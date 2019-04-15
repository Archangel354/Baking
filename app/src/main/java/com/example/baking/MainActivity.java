package com.example.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Adapter for the gridview of movies from the JSON data */
    private RecipeAdapter rAdapter;
    public final static String RECIPESTRING = "https://api.themoviedb.org/3/movie/popular?api_key=02ff7187d940e5bd15cd5acd2b41b63e";
    private RecyclerView rRecyclerView;
    private ArrayList<RecipeList> rRecipeList;



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
    }
}
