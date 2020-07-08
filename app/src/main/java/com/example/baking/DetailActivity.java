package com.example.baking;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
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
import static com.example.baking.MainActivity.EXTRA_RECIPENAME;
import static com.example.baking.MainActivity.EXTRA_STEPS;


public class DetailActivity extends AppCompatActivity implements DetailAdapter.OnItemClickListener{

    private RecyclerView dRecyclerView;
    private ArrayList<BakingModel.Steps> dStepsList;
    private SharedPreferences mSharedPreferences;

    /** Adapter for the gridview of movies from the JSON data */
    private DetailAdapter dAdapter;
    public static final String EXTRA_VIDEO = "video";
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPLIST = "steplist";
    public static final String EXTRA_POSITION = "position";
    public static final String WIDGET_PREFERENCES = "widgetinfo";
    public static final String RECIPE_KEY = "recipename";
    public static final String INGREDIENTS_KEY = "ingredientlist";


    //Bundle sSavedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dRecyclerView =  findViewById(R.id.recyclerDetail_view);
        mSharedPreferences = getSharedPreferences("widgetinfo",MODE_PRIVATE);



        Intent intent = getIntent();
        String recipename = intent.getStringExtra(EXTRA_RECIPENAME);
        String ingredients = intent.getStringExtra(EXTRA_INGREDIENTS);
        ArrayList steps = intent.getStringArrayListExtra(EXTRA_STEPS);
        dStepsList = steps;

        dAdapter = new DetailAdapter(DetailActivity.this, dStepsList);
        dRecyclerView.setHasFixedSize(true);
        dRecyclerView.setAdapter(dAdapter);
        dAdapter.setOnItemClickListener(DetailActivity.this);

        dRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));

        TextView textIngredients = findViewById(R.id.txtIngredientsList);
        textIngredients.setText(ingredients);

        Intent widgetIntent = new Intent(this, RecipeWidget.class);
        widgetIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        widgetIntent.putExtra(EXTRA_RECIPENAME, recipename);
        widgetIntent.putExtra(EXTRA_INGREDIENTS, ingredients);
        sendBroadcast(widgetIntent);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(RECIPE_KEY, recipename);
        editor.putString(INGREDIENTS_KEY, ingredients);
        if (editor.commit()){
            Toast.makeText(this, "Saved recipe and ingredients",Toast.LENGTH_LONG).show();
        }

        Log.i("TABLET", "recipe name is: " + recipename);
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(DetailActivity.this,"Card " + position + " pressed", Toast.LENGTH_SHORT).show();
        Log.i("OnItemClick", "DetailActivity");
        Intent stepDetailIntent = new Intent(this, StepDetailActivity.class);
        BakingModel.Steps clickedItem = dStepsList.get(position);
        String tDescription = clickedItem.getDescription();
        String tVideoURL = clickedItem.getVideoURL();



        if (this.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
            Log.i("TABLET", "before sSavedInstanceState step " + tDescription);
            Log.i("TABLET", "smallestScreenWidthDp: " + this.getResources().getConfiguration().smallestScreenWidthDp);

            Log.i("TABLET", "step " + tDescription);

            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle arguments = new Bundle();
            arguments.putString(EXTRA_STEP, tDescription);
            arguments.putString(EXTRA_VIDEO, tVideoURL);
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .replace(R.id.step_container, stepFragment)
                    .commit();
            //}
        } else {


            Log.i("OnItemClick", "step " + tDescription);
            Log.i("OnItemClick", "video " + tVideoURL);
            Log.i("OnItemClick", "steps " + dStepsList);
            Log.i("TABLET", "smallestScreenWidthDp: " + this.getResources().getConfiguration().smallestScreenWidthDp);

            //Bundle arguments = new Bundle();
            stepDetailIntent.putParcelableArrayListExtra(EXTRA_STEPLIST, dStepsList);
            stepDetailIntent.putExtra(EXTRA_POSITION, position);
            //stepDetailIntent.putExtras(arguments);
            startActivity(stepDetailIntent);
        }
    }
}
