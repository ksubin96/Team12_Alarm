package com.example.team12_alarm.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String ACTION_RESTART_SERVICE = "Restart";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_RESTART_SERVICE)) {
            Intent in = new Intent(context, AlarmService.class);
            context.startService(in);
        }
    }
}
