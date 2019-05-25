package com.example.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import static com.example.baking.MainActivity.EXTRA_INGREDIENTS;


public class DetailActivity extends AppCompatActivity {

    private RecyclerView dRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra(EXTRA_INGREDIENTS);

        TextView textIngredients = findViewById(R.id.txtIngredientsList);
        //dRecyclerView = findViewById(R.id.recyclerDetail_view);
        textIngredients.setText(ingredients);

    }
}
