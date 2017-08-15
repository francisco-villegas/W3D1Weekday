package com.example.francisco.w3d1weekday;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.francisco.w3d1weekday.services.BoundServiceAdd5;
import com.example.francisco.w3d1weekday.services.ServiceRecycle;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    BoundServiceAdd5 myBoundService;
    ServiceRecycle myServiceRecycle;
    boolean isBound = false;
    boolean isBoundRecycler = false;
    TextView tv1, tv2, tvrandom;
    int random = -1;

    RecyclerView rvRandomsList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RandomListAdapter randomsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tvrandom = (TextView) findViewById(R.id.tvrandom);

        Intent intent = getIntent();
        int number = Integer.parseInt(intent.getStringExtra("value1"));
        int random = Integer.parseInt(intent.getStringExtra("value2"));

        if(random != -1){
            tvrandom.setText(""+random);
        }
        else{
            tvrandom.setText("You have to bind the random service in the previous activity in order to get a random number");
        }

        tv2.setText(tv2.getText().toString()+" ("+number+")");

        Log.d(TAG, "onCreate: "+number);

        Intent boundIntent = new Intent(this, BoundServiceAdd5.class);
        boundIntent.putExtra("value2",""+number);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        Intent recycleboundIntent = new Intent(this, ServiceRecycle.class);
        recycleboundIntent.putExtra("value3",""+random);
        bindService(recycleboundIntent, recycleserviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (isBound)
                unbindService(serviceConnection);
        }catch(Exception ex){}
//

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            BoundServiceAdd5.MyBinder myBinder = (BoundServiceAdd5.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: " + myBoundService.add5());
            tv1.setText(String.valueOf(myBoundService.add5()));
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };

    ServiceConnection recycleserviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            ServiceRecycle.MyBinder myBinder = (ServiceRecycle.MyBinder) iBinder;
            myServiceRecycle = myBinder.getService();

            ArrayList<Randoms> randomsList = myServiceRecycle.getRecyclerViewList();
            rvRandomsList = (RecyclerView) findViewById(R.id.rvRandomsList);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            itemAnimator = new DefaultItemAnimator();
            rvRandomsList.setLayoutManager(layoutManager);
            rvRandomsList.setItemAnimator(itemAnimator);

            //initialize the adapter
            randomsListAdapter = new RandomListAdapter(randomsList);
            rvRandomsList.setAdapter(randomsListAdapter);

            randomsListAdapter.notifyDataSetChanged();

            isBoundRecycler = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBoundRecycler = false;
        }
    };
}
