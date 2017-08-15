package com.example.francisco.w3d1weekday.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundServiceAdd5 extends Service {
    private static final String TAG = "BoundServiceAdd5";
    IBinder iBinder = new BoundServiceAdd5.MyBinder();
    int number;

    public BoundServiceAdd5() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        number = Integer.parseInt(intent.getStringExtra("value2"));
        Log.d(TAG, "onCreate: "+number);
        return iBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class MyBinder extends Binder {

        public BoundServiceAdd5 getService(){
            return BoundServiceAdd5.this;
        }

    }

    public int add5(){
        return number+5;
    }
}
