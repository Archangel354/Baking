package com.example.baking;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.baking.DetailActivity.EXTRA_POSITION;
import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_STEPLIST;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;

import com.example.baking.models.BakingModel;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.Serializable;
import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPrevious;
    private Button btnNext;
    private BakingModel.Steps steps;
    private ArrayList<BakingModel.Steps> dStepsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);


        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        final Intent stepDetailIntent = getIntent();
        int position = stepDetailIntent.getIntExtra(EXTRA_POSITION, 0);
        ArrayList<BakingModel.Steps> stepList = stepDetailIntent.getParcelableArrayListExtra(EXTRA_STEPLIST);
        String sDescription = stepList.get(position).getDescription();
        String sUrlString = stepList.get(position).getVideoURL();
        Integer arraySize = stepList.size();
        String mDescription = stepList.get(position + 1).getDescription();

        Log.i("StepDetailActivity", "position: " + position + ".");
        Log.i("StepDetailActivity", "description: " + sDescription + ".");
        Log.i("StepDetailActivity", "stepList: " + stepList + ".");

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = stepDetailIntent.getIntExtra(EXTRA_POSITION, 0);
                Toast.makeText(StepDetailActivity.this, "Previous position is: " + position, Toast.LENGTH_SHORT).show();
                if (position <= 0) {
                    Toast.makeText(StepDetailActivity.this, "No previous position available " + position, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StepDetailActivity.this, "Next Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        if (savedInstanceState == null) {
            // In two-pane mode, add initial BodyPartFragments to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();
            Log.i("StepDetailActivity", "mDescription: " + mDescription + ".");

            //String step = getIntent().getStringExtra(EXTRA_STEP);
            Bundle arguments = new Bundle();
            arguments.putString(EXTRA_STEP, sDescription);
            arguments.putString(EXTRA_VIDEO, sUrlString);
            DescriptionFragment descriptionFragment = new DescriptionFragment();
            descriptionFragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .add(R.id.description_container, descriptionFragment)
                    .commit();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        }
       // TextView textStep = findViewById(R.id.txtRecipeStepInstruction);
       // textStep.setText(step);




    @Override
    public void onClick(View v) {
    }
}