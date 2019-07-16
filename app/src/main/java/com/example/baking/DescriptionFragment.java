package com.example.baking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baking.models.BakingModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.baking.DetailActivity.EXTRA_STEP;


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
    protected String mStep;

  //  private OnFragmentInteractionListener mListener;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(EXTRA_STEP)) {
            mStep = getArguments().getString(EXTRA_STEP);
            Log.i("DescriptionFragment","mStep: " +mStep );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);

        Log.i("DescriptionFragment oCV","mStep: " +mStep );

        // Load the saved state (the list of images and list index) if there is one
        if(savedInstanceState != null) {
            mDescriptionIds = savedInstanceState.getIntegerArrayList(DESCRIPTION_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //EXTRA_STEP
        if (mStep != null) {
            // Inflate the Android-Me fragment layout
            ((TextView) rootView.findViewById(R.id.step_description)).setText(mStep);
        }
        else {
            Log.i("DescriptionFragment","steps is null");
        }

        // Inflate the layout for this fragment
        return rootView;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
