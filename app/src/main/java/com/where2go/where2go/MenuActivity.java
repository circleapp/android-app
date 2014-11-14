package com.where2go.where2go;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.where2go.sdk.Place;


public class MenuActivity extends Activity {


    protected App app;

    private static final String LOG_TAG = "MenuActivity";
    protected ProgressBar pb;
    protected Button facebookBtn;
    protected Button playBtn;
    protected Button favoritesBtn;
    protected Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activiy);

        menu = null;
        app = (App) this.getApplicationContext();
        pb = (ProgressBar) findViewById(R.id.progressBar);
        facebookBtn = (Button) findViewById(R.id.facebook_btn);
        playBtn = (Button) findViewById(R.id.play_btn);
        favoritesBtn = (Button) findViewById(R.id.favorites);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(app.isUserLogged()){
            toggleUI(true);
        }
    }

    private void toggleUI(boolean userLogged){
        if(userLogged){
            pb.setVisibility(View.GONE);
            facebookBtn.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
            favoritesBtn.setVisibility(View.VISIBLE);
        }else{
            facebookBtn.setVisibility(View.VISIBLE);
            playBtn.setVisibility(View.GONE);
            favoritesBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activiy, menu);
        if(app.isUserLogged()){
            menu.setGroupVisible(R.id.group_menu, true);
        }else{
            menu.setGroupVisible(R.id.group_menu, false);
        }
        return true;
    }

    public void letsPlay(View v) {
        Intent game = new Intent(this, GameActivity.class);
        startActivity(game);
    }

    public void favs(View v) {
        //Intent favs = new Intent(this, FavsActivity.class);
        ParseQuery<ParseObject> place = ParseQuery.getQuery("Place");
        place.getInBackground("BvaY9PC4v5", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                Intent favs = new Intent(MenuActivity.this, PlaceActivity.class);
                favs.putExtra("place", new Place(parseObject));
                startActivity(favs);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }


    public void loginFacebook(View v) {
        facebookBtn.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        ParseFacebookUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                pb.setVisibility(View.GONE);
                if (user == null) {
                    facebookBtn.setVisibility(View.VISIBLE);
                } else { //mUser.isNew()
                    app.loginUser(user);
                    menu.setGroupVisible(R.id.group_menu, true);
                    toggleUI(true);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            app.mUser.logOut();

            if(ParseUser.getCurrentUser() == null){
                facebookBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.GONE);
                favoritesBtn.setVisibility(View.GONE);
                app.logoutUser();
                menu.setGroupVisible(R.id.group_menu, false);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
