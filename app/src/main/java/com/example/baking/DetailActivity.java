package com.example.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.baking.models.BakingModel;

import java.util.ArrayList;

import static com.example.baking.MainActivity.EXTRA_INGREDIENTS;


public class DetailActivity extends AppCompatActivity {

    private RecyclerView dRecyclerView;
    private ArrayList<BakingModel.Steps> dStepsList;

    /** Adapter for the gridview of movies from the JSON data */
    private DetailAdapter dAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dRecyclerView =  findViewById(R.id.recyclerDetail_view);
        // Create a new adapter that takes an empty list of movies as input
        dStepsList =
        dAdapter = new DetailAdapter(DetailActivity.this, dStepsList);
        dRecyclerView.setHasFixedSize(true);
        dRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));

        // Set the adapter on the {@link GridView} so the list can be populated in the user interface
        dRecyclerView.setAdapter(dAdapter);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra(EXTRA_INGREDIENTS);

        TextView textIngredients = findViewById(R.id.txtIngredientsList);
        textIngredients.setText(ingredients);

    }
}
