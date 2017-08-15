package com.example.francisco.w3d1weekday.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.d(TAG, "onHandleIntent: " + Thread.currentThread());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch(intent.getAction()){
            case "getRepo":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + Thread.currentThread() + " ");
                break;
            case "getProfile":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + Thread.currentThread());
                break;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
