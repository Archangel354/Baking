package com.example.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
                                JSONObject recipeName = response.getJSONObject(i);

                                String rName = recipeName.getString("name");

                                JSONArray ingredientArray = recipeName.getJSONArray("ingredients");

                                for (int j = 0; j < ingredientArray.length(); j++){
                                    JSONObject ingredientsObject = ingredientArray.getJSONObject(j);
                                    //ingredients = ingredients + "\n" + ingredientsObject.toString();
                                    ingredients =  ingredientsObject.toString() + "\n";

                                    Log.i("LOG MainActivity for lp", "ingredients: " + ingredients);


                                }
                                String rServings = recipeName.getString("servings");
                                String rImage = recipeName.getString("image");
                                Log.i("LOG MainActivity", "rName: " + rName);
                                Log.i("LOG MainActivity", "rServings: " + rServings);
                                Log.i("LOG MainActivity", "rImage: " + rImage + "\n");


                                rRecipeList.add(new RecipeList(rName, rServings, rImage));
                            }

                            rAdapter = new RecipeAdapter(MainActivity.this, rRecipeList);
                            //rRecyclerView.setAdapter(null);
                            rRecyclerView.setAdapter(rAdapter);
                            //rAdapter.setOnItemClickListener(MainActivity.this);
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

    }
}
