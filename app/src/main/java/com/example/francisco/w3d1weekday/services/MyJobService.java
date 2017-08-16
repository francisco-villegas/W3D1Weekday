package com.example.francisco.w3d1weekday.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by FRANCISCO on 15/08/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG, "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), MyScheduleService.class);
        startService(intent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
