package com.example.baking;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import static com.example.baking.DetailActivity.EXTRA_POSITION;
import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_STEPLIST;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;

import com.example.baking.models.BakingModel;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPrevious;
    private Button btnNext;
    private BakingModel.Steps steps;
    private ArrayList<BakingModel.Steps> dStepsList;
    private int mListIndex;
    private int stepListsize;
    private FrameLayout sFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Log.i("StepDetailActivity", "Orientation change: ");



        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        sFrameLayout = findViewById(R.id.step_container);

        final Intent stepDetailIntent = getIntent();
        final int position = stepDetailIntent.getIntExtra(EXTRA_POSITION, 0);
        mListIndex = position;
        final ArrayList<BakingModel.Steps> stepList = stepDetailIntent.getParcelableArrayListExtra(EXTRA_STEPLIST);
        final String sDescription = stepList.get(position).getDescription();
        final String sUrlString = stepList.get(position).getVideoURL();
        stepListsize = stepList.size();

        Log.i("StepDetailActivity", "position: " + position + ".");
        Log.i("StepDetailActivity", "description: " + sDescription + ".");
        Log.i("StepDetailActivity", "stepList: " + stepList + ".");

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex > 0) {

                        mListIndex--;
                        Log.i("Previous", "mListIndex " + mListIndex);
                        Log.i("Previous", "stepListsize " + stepListsize);

                        //Intent stepDetailIntent = new Intent(v.getContext(), StepDetailActivity.class);
                        BakingModel.Steps clickedItem = stepList.get(mListIndex);

                        Log.i("Previous", "step " + clickedItem.getDescription());
                        Log.i("Previous", "video " + clickedItem.getVideoURL());
                        Log.i("Previous", "steps " + stepList);
                        //Bundle arguments = new Bundle();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Bundle arguments = new Bundle();
                        arguments.putString(EXTRA_STEP, clickedItem.getDescription());
                        arguments.putString(EXTRA_VIDEO, clickedItem.getVideoURL());
                        StepFragment stepFragment = new StepFragment();
                        stepFragment.setArguments(arguments);
                        fragmentManager.beginTransaction()
                                .replace(R.id.step_container, stepFragment)
                                .commit();

                    } else {
                        Toast.makeText(StepDetailActivity.this, "No more steps before this!!", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(StepDetailActivity.this, "Next Pressed", Toast.LENGTH_SHORT).show();
                    /// Toast.makeText(StepDetailActivity.this, "Next position is: " + position, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(StepDetailActivity.this, "Next dStepsList is: " + dStepsList, Toast.LENGTH_SHORT).show();
                    if (mListIndex < stepListsize - 1) {

                        mListIndex++;
                        Log.i("Next", "mListIndex " + mListIndex);
                        Log.i("Next", "stepListsize " + stepListsize);

                        //Intent stepDetailIntent = new Intent(v.getContext(), StepDetailActivity.class);
                        BakingModel.Steps clickedItem = stepList.get(mListIndex);

                        Log.i("Next", "step " + clickedItem.getDescription());
                        Log.i("Next", "video " + clickedItem.getVideoURL());
                        Log.i("Next", "steps " + stepList);
                        //Bundle arguments = new Bundle();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Bundle arguments = new Bundle();
                        arguments.putString(EXTRA_STEP, clickedItem.getDescription());
                        arguments.putString(EXTRA_VIDEO, clickedItem.getVideoURL());
                        StepFragment stepFragment = new StepFragment();
                        stepFragment.setArguments(arguments);
                        fragmentManager.beginTransaction()
                                .replace(R.id.step_container, stepFragment)
                                .commit();

                    } else {
                        Toast.makeText(StepDetailActivity.this, "No more steps after this!!", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

        if ((this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                &&((this.getResources().getConfiguration().smallestScreenWidthDp < 600))){
            if((btnNext != null) && (btnPrevious != null)) {
                btnPrevious.setVisibility(View.GONE);
                btnNext.setVisibility(View.GONE);
            }


        }

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle arguments = new Bundle();
            arguments.putString(EXTRA_STEP, sDescription);
            arguments.putString(EXTRA_VIDEO, sUrlString);
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .add(R.id.step_container, stepFragment)
                    .commit();

        }
    }



    @Override
    public void onClick(View v) {
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            btnPrevious.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
//            sFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//            ));
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            btnPrevious.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}