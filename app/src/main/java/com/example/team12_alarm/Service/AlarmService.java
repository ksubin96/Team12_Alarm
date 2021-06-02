package com.example.team12_alarm.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.team12_alarm.Activities.AlarmActivity;

public class AlarmService extends Service {
    String TAG = "TAG+Service";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "AlarmService");
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        return super.onStartCommand(intent, flags, startId);
    }
}
