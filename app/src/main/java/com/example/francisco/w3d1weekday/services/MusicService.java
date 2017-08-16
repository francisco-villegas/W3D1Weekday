package com.example.francisco.w3d1weekday.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.francisco.w3d1weekday.R;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    static MediaPlayer mp3;
    int id;


    public MusicService() {

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        try{id = Integer.parseInt(intent.getStringExtra("id_music"));}catch (Exception ex){}
        switch(intent.getAction()){
            case "Play":
                Play();
                break;
            case "Pause":
                Pause();
                break;
            case "Stop":
                Stop();
                break;
            case "stopForeground":
                //stop notification
                Log.d(TAG, "onSTOP: ");
                stopForeground(true);
                Stop();
                stopSelf();
                break;
        }

        return Service.START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    public void Play() {
        if(mp3 ==null) {
            mp3 = MediaPlayer.create(getApplicationContext(), id);
            mp3.setLooping(true);
            mp3.start();
            Intent stopIntent = new Intent(this, MusicService.class);
            stopIntent.setAction("stopForeground");
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
            Notification notification = new NotificationCompat.Builder(this).setContentTitle("Music Player")
                    .setContentText("Music playing in foreground").setSmallIcon(R.drawable.bruno).setOngoing(true)
                    .addAction(R.drawable.ic_stop, "STOP MUSIC", pendingIntent).build();
            startForeground(1, notification);

        }
        if(!mp3.isPlaying()) {
            mp3.setLooping(true);
            mp3.start();
        }
    }
    public void Pause() {
        if (mp3 != null)
            if (mp3.isPlaying())
                mp3.pause();
    }

    public void Stop(){
        if (mp3 != null) {
            mp3.stop();
            mp3.release();
            mp3 = null;
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
