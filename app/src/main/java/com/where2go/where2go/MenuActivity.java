package com.where2go.where2go;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activiy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activiy, menu);
        return true;
    }

    public void letsPlay(View v){
        Intent game = new Intent(this, GameActivity.class);
        startActivity(game);
    }

    public void favs(View v){
        Intent favs = new Intent(this, FavsActivity.class);
        startActivity(favs);
    }

    public void loginFacebook(View v){
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        Button facebookBtn = (Button) findViewById(R.id.facebook_btn);
        facebookBtn.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
