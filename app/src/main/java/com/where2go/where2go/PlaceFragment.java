package com.where2go.where2go;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.where2go.api.objects.Place;


public class PlaceFragment extends Fragment {

    protected TextView mPlaceName;
    protected TextView mPlaceShortLocation;
    protected Place place;
    protected boolean next;
    protected Button mNextPlace;

    private OnFragmentInteractionListener mListener;

    public static PlaceFragment newInstance(Place place, boolean next) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putBoolean("next", next);
        args.putSerializable("place", place);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            place = (Place) getArguments().getSerializable("place");
            next = getArguments().getBoolean("next");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_place, container, false);
        mPlaceName = (TextView) v.findViewById(R.id.place_name);
        mPlaceShortLocation = (TextView) v.findViewById(R.id.place_short_location);
        mNextPlace = (Button) v.findViewById(R.id.nextPlace);

        mPlaceName.setText(place.getName());
        mPlaceShortLocation.setText(place.getShortLocation());

        if(!next){
            mNextPlace.setVisibility(Button.GONE);
        }

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
