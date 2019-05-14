package com.example.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;


public class DetailActivity extends AppCompatActivity {

    private RecyclerView dRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dRecyclerView = findViewById(R.id.recyclerDetail_view);
    }
}
