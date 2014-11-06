package com.where2go.where2go;
/**
 * Created by henocdz on 5/11/14.
 */

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate(){
        Parse.initialize(this, "pjDqEx0JZwcC6mWcycXAQ6lIWaldcGtynfLIkR0B", "1pCIRvx6NNtYEZwuVNWgOeEvkf4A4NlqF6wOtJJs");
    }
}
