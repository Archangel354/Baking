package com.example.baking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleExoPlayerView m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        String step = intent.getStringExtra(EXTRA_STEP);
        String videoURL = intent.getStringExtra(EXTRA_VIDEO);

        TextView textStep = findViewById(R.id.txtRecipeStepInstruction);
        textStep.setText(step);

    }

    @Override
    public void onClick(View v) {

    }
}
