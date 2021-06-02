package com.example.team12_alarm.Service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;

import com.example.team12_alarm.Activities.BatteryActivity;
import com.example.team12_alarm.Activities.BatteryAlarmScreenActivity;

public class BatteryAlarmService extends Service {
    private static int batPct;
    int a;

    private BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();

            if(intent.ACTION_BATTERY_CHANGED.equals(action)){

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                float batteryPct = level / (float)scale;

                batPct= (int)(batteryPct * 100)+a;

                if(batPct<= BatteryActivity.getBatSet()){
                    a=100;
                    Intent intent1= new Intent(getApplicationContext(), BatteryAlarmScreenActivity.class);
                    startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }


            }
        }
    };

    @Override
    public void onCreate() {
        registerReceiver(alarmReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(alarmReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
