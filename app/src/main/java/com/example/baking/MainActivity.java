package com.example.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.baking.models.BakingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {

    /** Adapter for the gridview of movies from the JSON data */
    private RecyclerView rRecyclerView;
    private RecipeAdapter rAdapter;
    public final static String RECIPESTRING = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private ArrayList<RecipeList> rRecipeList;
    private RequestQueue rRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rRecyclerView =  findViewById(R.id.recycler_view);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rRecipeList = new ArrayList<>();
        rRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
        Log.i("MainActivity", "END OF APP;onCreate");

    }

    private void parseJSON() {
        String url = RECIPESTRING;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String ingredients = null;

                            //JSONObject jsonObject = response.getJSONObject("name");
                            Log.i("LOG MainActivity", "parseJSON: " + response);

                            for (int i = 0; i < response.length(); i++) {
                                BakingModel bakingModel = new BakingModel();
                                bakingModel.setRecipeName();
                                JSONObject recipeName = response.getJSONObject(i);

                                String rName = recipeName.getString("name");

                                JSONArray ingredientArray = recipeName.getJSONArray("ingredients");
                                for (int j = 0; j < ingredientArray.length(); j++){
                                    JSONObject ingredientsObject = ingredientArray.getJSONObject(j);
                                    Double quantity = ingredientsObject.getDouble("quantity");
                                    String measure = ingredientsObject.getString("measure");
                                    String ingredient = ingredientsObject.getString("ingredient");
                                    Log.i("LOG MainActivity for lp", quantity + " " + measure + "\t\t" + ingredient + "\n");
                                }

                                JSONArray stepsArray = recipeName.getJSONArray("steps");
                                for (int k = 0; k < stepsArray.length(); k++){
                                    JSONObject stepsObject = stepsArray.getJSONObject(k);
                                    String shortDescription = stepsObject.getString("shortDescription");
                                    String description = stepsObject.getString("description");
                                    String videoURL = stepsObject.getString("videoURL");
                                    String thumbnailURL = stepsObject.getString("thumbnailURL");
                                    Log.i("LOG MainActivity for lp", shortDescription + " " + description + "\t" + videoURL + thumbnailURL + "\n");
                                }


                                String rServings = recipeName.getString("servings");
                                //String rImage = recipeName.getString("image");
                                Log.i("LOG MainActivity", "rName: " + rName);
                                Log.i("LOG MainActivity", "rServings: " + rServings);
                                rRecipeList.add(new RecipeList(rName, rServings));
                            }

                            rAdapter = new RecipeAdapter(MainActivity.this, rRecipeList);
                            rRecyclerView.setAdapter(rAdapter);
                            rAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
               Toast.makeText(MainActivity.this,"Card pressed", Toast.LENGTH_SHORT).show();
               Log.i("OnClick", "MainActivity");
        Intent detailIntent = new Intent(this, DetailActivity.class);
        startActivity(detailIntent);

    }
}
