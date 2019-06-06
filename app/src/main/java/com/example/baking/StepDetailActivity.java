package com.example.baking;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.ui.PlayerView;



public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Player sPlayer;

    private PlayerView sPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        // Initialize the player view
        sPlayerView = findViewById(R.id.imgInstructionVideo);

        // Load the "No video available" as the background image until a recipe video is available
        sPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.no_video));
        sPlayerView.setPlayer(sPlayer);
        sPlayer.setPlayWhenReady(true);

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
