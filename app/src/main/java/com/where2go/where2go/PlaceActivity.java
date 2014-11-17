package com.where2go.where2go;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.where2go.api.objects.Place;


public class PlaceActivity extends Activity {

    public static final String LOG_TAG = "PlaceActivity";
    protected Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_activiy);



        Intent intent = getIntent();
        this.place = (Place) intent.getSerializableExtra("place");

        getActionBar().setTitle(place.getName());

        //Put Fragment on place
        FragmentManager man = getFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        PlaceFragment placeFragment = PlaceFragment.newInstance(place);

        trans.add(R.id.fragment_view, placeFragment);
        trans.commit();
        //!Put fragment

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

    public void nextPlace(View v){
        Intent next = new Intent(this, PlaceActivity.class);
        next.putExtra("place", new Place());
        startActivity(next);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out_null);
    }

    public void exit(View v){
        Intent menu = new Intent(this, MenuActivity.class);
        menu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(menu);
        overridePendingTransition(R.anim.push_left_in_null, R.anim.push_left_out);
        finish();
    }

    public void writeReview(View v){
        Intent review = new Intent(this, ReviewActivity.class);
        startActivity(review);
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
