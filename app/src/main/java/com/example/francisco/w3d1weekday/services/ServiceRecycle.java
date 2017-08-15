package com.example.francisco.w3d1weekday.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.francisco.w3d1weekday.Randoms;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class ServiceRecycle extends Service {
    private static final String TAG = "MyBoundService";
    IBinder iBinder = new ServiceRecycle.MyBinder();
    ArrayList<Randoms> randomsList = new ArrayList<>();

    public ServiceRecycle() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        SecureRandom randomS = new SecureRandom();
        for(int i=0;i<Integer.parseInt(intent.getStringExtra("value3"));i++){
            randomsList.add(new Randoms(new BigInteger(130, randomS).toString(32)));
        }
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

        public ServiceRecycle getService(){
            return ServiceRecycle.this;
        }

    }

    public ArrayList<Randoms> getRecyclerViewList(){
        return randomsList;
    }
}
