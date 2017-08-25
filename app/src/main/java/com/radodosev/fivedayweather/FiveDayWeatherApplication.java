package com.radodosev.fivedayweather;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rado on 8/24/2017.
 */

public class FiveDayWeatherApplication extends Application {
    private static Context thisApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        thisApplicationContext = this.getApplicationContext();
    }

    public static Context getContext() {
        return thisApplicationContext;
    }
}
