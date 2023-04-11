package com.group8.saveit;

import android.app.Application;
import android.content.Context;

public class SaveItApp extends Application { //use to retrieve assets file in DatabaseHelper using whole app's context
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // ...
    }

    public static Context getAppContext() {
        return context;
    }
}
