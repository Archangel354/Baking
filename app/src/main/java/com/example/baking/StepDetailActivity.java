package com.example.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Integer galacticPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);


        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        final Intent stepDetailIntent = getIntent();
        final ArrayList<BakingModel.Steps> stepList = stepDetailIntent.getParcelableArrayListExtra(EXTRA_STEPLIST);
        final int sPosition = stepDetailIntent.getIntExtra(EXTRA_POSITION, 0);
        stepList.set(0, steps).setPosition(sPosition);
        galacticPosition = stepList.get(0).getPosition();

        final String sDescription = stepList.get(sPosition).getDescription();
        final String sUrlString = stepList.get(sPosition).getVideoURL();
        Integer arraySize = stepList.size();
        String mDescription = stepList.get(sPosition + 1).getDescription();
        steps.setPosition(sPosition);

        Log.i("StepDetailActivity", "position: " + sPosition + ".");
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

//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(StepDetailActivity.this, "Next Pressed", Toast.LENGTH_SHORT).show();
//               /// Toast.makeText(StepDetailActivity.this, "Next position is: " + position, Toast.LENGTH_SHORT).show();
//               // Toast.makeText(StepDetailActivity.this, "Next dStepsList is: " + dStepsList, Toast.LENGTH_SHORT).show();
//                Integer nextPosition = position + 1;
//                Log.i("Next", "nextPosition: " + nextPosition);
//                Log.i("Next", "stepList " + stepList);
//
//                Intent stepDetailIntent = new Intent(v.getContext(), StepDetailActivity.class);
//                BakingModel.Steps clickedItem = stepList.get(position + 1);
//                Log.i("Next", "step " + clickedItem.getDescription());
//                Log.i("Next", "video " + clickedItem.getVideoURL());
//                Log.i("Next", "steps " + stepList);
//                //Bundle arguments = new Bundle();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                Bundle arguments = new Bundle();
//                arguments.putString(EXTRA_STEP, clickedItem.getDescription());
//                arguments.putString(EXTRA_VIDEO,clickedItem.getVideoURL());
//                StepFragment stepFragment = new StepFragment();
//                stepFragment.setArguments(arguments);
//                fragmentManager.beginTransaction()
//                        .replace(R.id.step_container, stepFragment)
//                        .commit();
//
//            }
//        });

        btnNext.setOnClickListener(new BtnNextListener());


        if (savedInstanceState == null) {
            // In two-pane mode, add initial BodyPartFragments to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();
            Log.i("StepDetailActivity", "mDescription: " + mDescription + ".");

            //String step = getIntent().getStringExtra(EXTRA_STEP);
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

    public  class BtnNextListener implements View.OnClickListener
    {
        Integer sPosition = steps.getPosition();
        //String mDescription = stepList.get(sPosition + 1).getDescription();

        @Override
        public void onClick(View v) {

                            Integer nextPosition = sPosition + 1;
                Log.i("Next", "nextPosition: " + nextPosition);
                Log.i("Next", "stepList " + dStepsList);

                Intent stepDetailIntent = new Intent(v.getContext(), StepDetailActivity.class);
                BakingModel.Steps clickedItem = dStepsList.get(nextPosition);
                Log.i("BtnNextListener", "step " + clickedItem.getDescription());
                Log.i("BtnNextListener", "video " + clickedItem.getVideoURL());
                Log.i("BtnNextListener", "steps " + dStepsList);
                //Bundle arguments = new Bundle();
                FragmentManager fragmentManager = getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString(EXTRA_STEP, clickedItem.getDescription());
                arguments.putString(EXTRA_VIDEO,clickedItem.getVideoURL());
                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(arguments);
                fragmentManager.beginTransaction()
                        .replace(R.id.step_container, stepFragment)
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