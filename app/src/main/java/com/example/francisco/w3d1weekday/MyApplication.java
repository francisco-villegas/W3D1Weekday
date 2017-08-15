package com.example.francisco.w3d1weekday;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BeforeStarts", "onCreate: ");
    }
}
