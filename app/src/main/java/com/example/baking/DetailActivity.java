package com.example.baking;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baking.models.BakingModel;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.baking.MainActivity.EXTRA_INGREDIENTS;
import static com.example.baking.MainActivity.EXTRA_STEPS;


public class DetailActivity extends AppCompatActivity implements DetailAdapter.OnItemClickListener{

    private RecyclerView dRecyclerView;
    private ArrayList<BakingModel.Steps> dStepsList;

    /** Adapter for the gridview of movies from the JSON data */
    private DetailAdapter dAdapter;
    public static final String EXTRA_VIDEO = "video";
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPLIST = "steplist";
    public static final String EXTRA_POSITION = "position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dRecyclerView =  findViewById(R.id.recyclerDetail_view);

        Intent intent = getIntent();
        String ingredients = intent.getStringExtra(EXTRA_INGREDIENTS);
        ArrayList steps = intent.getStringArrayListExtra(EXTRA_STEPS);
        dStepsList = steps;
        Log.i("DetailActivity", "steps " + steps);

        dAdapter = new DetailAdapter(DetailActivity.this, dStepsList);
        dRecyclerView.setHasFixedSize(true);
        dRecyclerView.setAdapter(dAdapter);
        dAdapter.setOnItemClickListener(DetailActivity.this);

        dRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));

        TextView textIngredients = findViewById(R.id.txtIngredientsList);
        textIngredients.setText(ingredients);
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(DetailActivity.this,"Card " + position + " pressed", Toast.LENGTH_SHORT).show();
        Log.i("OnItemClick", "DetailActivity");
        Intent stepDetailIntent = new Intent(this, StepDetailActivity.class);
        BakingModel.Steps clickedItem = dStepsList.get(position);
        Log.i("OnItemClick", "step " + clickedItem.getDescription());
        Log.i("OnItemClick", "video " + clickedItem.getVideoURL());
        Log.i("OnItemClick", "steps " + dStepsList);
        //Bundle arguments = new Bundle();
        stepDetailIntent.putParcelableArrayListExtra(EXTRA_STEPLIST, dStepsList);
        stepDetailIntent.putExtra(EXTRA_POSITION, position);
        //stepDetailIntent.putExtras(arguments);
        startActivity(stepDetailIntent);
    }
}
