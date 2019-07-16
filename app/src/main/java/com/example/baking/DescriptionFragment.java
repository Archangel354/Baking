package com.example.baking;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import static com.example.baking.DetailActivity.EXTRA_POSITION;
import static com.example.baking.DetailActivity.EXTRA_STEP;
import static com.example.baking.DetailActivity.EXTRA_VIDEO;
import static com.example.baking.StepDetailActivity.EXTRA_DETAILSTEPLIST;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DescriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.

 */
public class DescriptionFragment extends Fragment {
    // DONE: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DESCRIPTION_ID_LIST = "description_ids";
    public static final String LIST_INDEX = "list_index";

    // Tag for logging
    private static final String TAG = "DescriptionFragment";

    // DONE: Rename and change types of parameters
    private ArrayList<Integer> mDescriptionIds;
    private int mListIndex;

    private BakingModel.Steps steps;
    protected ArrayList<BakingModel.Steps> mStep;
    private String dDescription;
    private SimpleExoPlayer sPlayer;
    private PlayerView sPlayerView;
    private ImageView imgNoVideo;
    private Integer dPosition;

  //  private OnFragmentInteractionListener mListener;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(EXTRA_DETAILSTEPLIST)) {
            mStep = getArguments().getParcelable(EXTRA_DETAILSTEPLIST);
            dPosition =getArguments().getInt(EXTRA_POSITION);
            Log.i("DescriptionFragment","mStep: " +mStep );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);

        Log.i("DescriptionFragment oCV","mStep: " +mStep );
       //dDescription = mStep.

        // Load the saved state (the list of images and list index) if there is one
        if(savedInstanceState != null) {
            mDescriptionIds = savedInstanceState.getIntegerArrayList(DESCRIPTION_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //EXTRA_STEP
        if (mStep != null) {
            // Inflate the Android-Me fragment layout
            ((TextView) rootView.findViewById(R.id.step_description)).setText(dDescription);
            if (mStep.get(dPosition).getVideoURL() != null && !mStep.get(dPosition).getVideoURL().equals("")) {
                sPlayerView.setVisibility(View.VISIBLE);
                initializeMediaSession();
                initializePlayer(Uri.parse(mStep.getVideoURL()));
            } else {
                mPlayerView.setVisibility(View.GONE);
            }
        }
        else {
            Log.i("DescriptionFragment","steps is null");
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String step = intent.getStringExtra(EXTRA_STEP );
        String urlString = intent.getStringExtra(EXTRA_VIDEO);
        Uri myUri = Uri.parse(urlString);
        Log.i("StepDetailActivity", "urlString: " + urlString + ".");
        if (urlString.isEmpty()) {
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
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
