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
    private SimpleExoPlayer sPlayer;
    private PlayerView sPlayerView;
    private ImageView imgNoVideo;
    private Button btnPrevious;
    private Button btnNext;
    private BakingModel.Steps steps;

    private ArrayList<BakingModel.Steps> dStepsList;
    public static final String EXTRA_DETAILSTEPLIST = "detailsteplist";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        // Initialize the player view
        sPlayerView = findViewById(R.id.imgInstructionVideo);
        imgNoVideo = findViewById(R.id.imgNoVideo);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        final Intent stepDetailIntent = getIntent();
        //Bundle data = stepDetailIntent.getExtras();
        //BakingModel.Steps mSteps = (BakingModel.Steps) data.getParcelable(EXTRA_STEPLIST);
        int position = stepDetailIntent.getIntExtra(EXTRA_POSITION, 0);
        String description = stepDetailIntent.getStringExtra(EXTRA_STEP);
        ArrayList<BakingModel.Steps> sStepList = stepDetailIntent.getParcelableArrayListExtra(EXTRA_STEPLIST);

        Integer arraySize = sStepList.size();
        String mDescription = sStepList.get(position + 1).getDescription();
        Log.i("StepDetailActivity", "position: " + position + ".");
        Log.i("StepDetailActivity", "description: " + description + ".");
        Log.i("StepDetailActivity", "stepList: " + sStepList + ".");

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

            String step = getIntent().getStringExtra(EXTRA_STEP);
            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList(EXTRA_DETAILSTEPLIST, sStepList);
            arguments.putInt(EXTRA_POSITION,position);
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

       // TextView textStep = findViewById(R.id.txtRecipeStepInstruction);
       // textStep.setText(step);
    }

    @Override
    protected void onStop() {
        sPlayerView.setPlayer(null);
        if (sPlayer != null) {
            sPlayer.release();
        }
        sPlayer = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
    }
}