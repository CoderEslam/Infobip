package com.doubleclick.infobip;

import android.app.Application;
import android.content.Context;

public class App extends Application {

     static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
