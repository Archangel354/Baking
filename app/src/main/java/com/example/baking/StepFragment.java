package com.example.baking;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baking.models.BakingModel;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.

 */
public class StepFragment extends Fragment {
    // DONE: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DESCRIPTION_ID_LIST = "description_ids";
    public static final String LIST_INDEX = "list_index";

    // Tag for logging
    private static final String TAG = "StepFragment";

    // DONE: Rename and change types of parameters
    private ArrayList<Integer> mDescriptionIds;
    private int mListIndex;
    private BakingModel.Steps steps;
    protected String mUrlString = null;

    @BindView(R.id.imgNoVideo)
    ImageView imgNoVideo;
   String mStep;

    // new stuff based on example
    @BindView(R.id.imgInstructionVideo)
    SimpleExoPlayerView sPlayerView;
    private SimpleExoPlayer sPlayer;
    private Unbinder unbinder;

    public StepFragment() { /** Required empty public constructor **/    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(EXTRA_STEP)) {
            mStep = getArguments().getString(EXTRA_STEP);
            Log.i("StepFragment","mStep: " +mStep );
        }

        if (getArguments().containsKey(EXTRA_VIDEO)) {
            mUrlString = getArguments().getString(EXTRA_VIDEO);
            Log.i("StepFragment","mUrlString: " +mUrlString );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //        // Load the saved state (the list of images and list index) if there is one
        if(savedInstanceState != null) {
            mDescriptionIds = savedInstanceState.getIntegerArrayList(DESCRIPTION_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        if ((this.getResources().getConfiguration().smallestScreenWidthDp < 600) && ((mStep != null) &&
                (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT))) {
            View rootView = inflater.inflate(R.layout.fragment_step, container, false);
            unbinder = ButterKnife.bind(this, rootView);
            TextView text = rootView.findViewById(R.id.step_description);

            text.setText(mStep);
            getPlayer();
            return rootView;
        }

        if (this.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
            View rootView = inflater.inflate(R.layout.fragment_step, container, false);
            unbinder = ButterKnife.bind(this, rootView);
            TextView text = rootView.findViewById(R.id.step_description);
            Log.i("StepFragment","mStep is: " + mStep);
            text.setText(mStep);
            getPlayer();
            return rootView;
        }



        Log.i("StepFragment","returning null and mStep is: " + mStep);

       return null;
        }

        private void getPlayer(){

        String videoURL = mUrlString;

            if (videoURL.isEmpty()) {
                sPlayerView.setVisibility(View.GONE);
                imgNoVideo.setVisibility(View.VISIBLE);
            }
            else {
                sPlayerView.setVisibility(View.VISIBLE);
                imgNoVideo.setVisibility(View.GONE);
            }

            Handler mainHandler = new Handler();

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            sPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);

           // Load the default controller
            sPlayerView.setUseController(true);
            sPlayerView.requestFocus();

            // Load the SimpleExoPlayerView with the created player
            sPlayerView.setPlayer(sPlayer);

            // Measures bandwidth during playback. Can be null if not required.
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();

            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    getContext(),
                    Util.getUserAgent(getContext(), "MyAppName"),
                    defaultBandwidthMeter);

            // Produces Extractor instances for parsing the media data.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource(
                    Uri.parse(videoURL),
                    dataSourceFactory,
                    extractorsFactory,
                    null,
                    null);

            // Prepare the player with the source.
            sPlayer.prepare(videoSource);

            // Autoplay the video when the player is ready
            sPlayer.setPlayWhenReady(true);

        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        // Release the player when it is not needed
        sPlayer.release();
    }
}
