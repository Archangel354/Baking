package com.example.baking;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleExoPlayer sPlayer;
    private PlayerView sPlayerView;
    private ImageView imgNoVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        // Initialize the player view
        sPlayerView = findViewById(R.id.imgInstructionVideo);
        imgNoVideo = findViewById(R.id.imgNoVideo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String step = intent.getStringExtra(EXTRA_STEP);
        String urlString = intent.getStringExtra(EXTRA_VIDEO);
        Uri myUri = Uri.parse(urlString);
        Log.i("StepDetailActivity", "urlString: " + urlString + ".");
        if (urlString.isEmpty()) {
            Toast.makeText(this,"No video",
                    Toast.LENGTH_SHORT).show();
            sPlayerView.setVisibility(View.GONE);
            imgNoVideo.setVisibility(View.VISIBLE);
        }
        else {
            sPlayerView.setVisibility(View.VISIBLE);
            imgNoVideo.setVisibility(View.GONE);
            sPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
            sPlayerView.setPlayer(sPlayer);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    this,
                    Util.getUserAgent(this, getString(R.string.app_name)));
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(myUri);

            sPlayer.prepare(mediaSource);
            sPlayer.setPlayWhenReady(true);
        }
        TextView textStep = findViewById(R.id.txtRecipeStepInstruction);
        textStep.setText(step);
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