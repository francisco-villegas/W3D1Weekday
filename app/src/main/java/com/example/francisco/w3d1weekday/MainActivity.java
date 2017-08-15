package com.example.francisco.w3d1weekday;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.francisco.w3d1weekday.services.MyBoundService;
import com.example.francisco.w3d1weekday.services.MyIntentService;
import com.example.francisco.w3d1weekday.services.MyNormalService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button btnStartNormalService, btnStopNormalService, btnStartIntentService, btnBindService, btnUnBindService, btnSecondActivity;
    MyBoundService myBoundService;
    boolean isBound = false;
    EditText etnumber;
    int random=-1;
    ImageButton btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartNormalService = (Button) findViewById(R.id.btnStartNormalService);
        btnStopNormalService = (Button) findViewById(R.id.btnStopNormalService);
        btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnBindService = (Button) findViewById(R.id.btnUnBindService);
        btnSecondActivity = (Button) findViewById(R.id.btnSecondActivity);

        etnumber = (EditText) findViewById(R.id.etnumber);

        btnStartNormalService.setOnClickListener(this);
        btnStopNormalService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnSecondActivity.setOnClickListener(this);

        Log.d("AfterStart", "onCreate: ");

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnStop = (ImageButton) findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);

        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch (view.getId()){
            case R.id.btnStartNormalService:
                normalIntent.putExtra("data","This is a normal service");
                startService(normalIntent);
                break;
            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;
            case R.id.btnStartIntentService:
                intIntent.setAction("getRepo");
                intIntent.putExtra("data","This is an intent service repo");
                startService(intIntent);
                break;
            case R.id.btnBindService:
//                intIntent.setAction("getProfile");
//                intIntent.putExtra("data","This is an intent service profile");
//                startService(intIntent);
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

                break;
            case R.id.btnUnBindService:
                try {
                    if (isBound)
                        unbindService(serviceConnection);
                }catch(Exception ex){}
                break;
            case R.id.btnSecondActivity:
                try {
                    Intent intent = new Intent(this, SecondActivity.class);
                    int number = Integer.parseInt(etnumber.getText().toString());
                    intent.putExtra("value1", ""+number);
                    intent.putExtra("value2", ""+random);
                    startActivity(intent);
                }catch(Exception ex){
                    Toast.makeText(this, "You have to add a number!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnPlay:
                break;
            case R.id.btnPause:
                break;
            case R.id.btnStop:
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            random = myBoundService.getRandomData();
            Log.d(TAG, "onServiceConnected: " + random);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };
}
