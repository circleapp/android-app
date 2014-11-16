package com.where2go.where2go;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.where2go.api.Place;


public class PlaceActivity extends Activity {

    public static final String LOG_TAG = "PlaceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_activiy);
        //Put Fragment on place
        FragmentManager man = getFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        PlaceFragment placeFragment = new PlaceFragment();

        trans.add(R.id.fragment_view, placeFragment);
        trans.commit();
        //!Put fragment

        Intent intent = getIntent();
        Place place = (Place) intent.getSerializableExtra("place");

        Log.i(LOG_TAG, place.getName());

/*        ParseQuery<ParseObject> places = ParseQuery.getQuery("Place");
        places.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for(ParseObject place : parseObjects){
                    //Log.i(LOG_TAG, place.getParseGeoPoint("location"));
                }
            }
        });*/
    }

    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in_null, R.anim.push_left_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.place_activiy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
