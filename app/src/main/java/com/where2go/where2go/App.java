package com.where2go.where2go;
/**
 * Created by henocdz on 5/11/14.
 */

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseFacebookUtils;

public class App extends Application {

    public ParseUser user;

    @Override
    public void onCreate() {
        Log.i("Initializacion", "------------->");
        Parse.initialize(this, "pjDqEx0JZwcC6mWcycXAQ6lIWaldcGtynfLIkR0B", "1pCIRvx6NNtYEZwuVNWgOeEvkf4A4NlqF6wOtJJs");
        ParseFacebookUtils.initialize("748940688492803");
    }


}
