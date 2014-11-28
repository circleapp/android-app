package com.where2go.where2go;
/**
 * Created by henocdz on 5/11/14.
 */

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.Session;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseFacebookUtils;

import java.util.Map;

public class App extends Application {

    public ParseUser mUser;
    private static final String LOG_TAG = "Where2Go";
    private static final String mSharedPreferencesName = "w2gPreferences";
    private static final String mSessionKey = "userIsLogged";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mPreferencesEditor;

    @Override
    public void onCreate() {
        mPreferences = getSharedPreferences(mSharedPreferencesName, Context.MODE_PRIVATE);
        mPreferencesEditor = mPreferences.edit();

        Parse.initialize(this, "pjDqEx0JZwcC6mWcycXAQ6lIWaldcGtynfLIkR0B", "1pCIRvx6NNtYEZwuVNWgOeEvkf4A4NlqF6wOtJJs");
        ParseFacebookUtils.initialize("748940688492803");
        isUserLogged();

    }

    public void loginUser(ParseUser user){
        mUser = user;
        mPreferencesEditor.putBoolean(mSessionKey, true);
        mPreferencesEditor.apply();
    }

    public void logoutUser(){
        mPreferencesEditor.putBoolean(mSessionKey, false);

        Session s = Session.getActiveSession();
        if(s != null){
            s.closeAndClearTokenInformation();
        }
    }

    public boolean isUserLogged(){
        if( mPreferences.getBoolean(mSessionKey, false) ){
            mUser = ParseUser.getCurrentUser();
            if(mUser != null){
                return true;
            }
            logoutUser();
        }
        return false;
    }


}
