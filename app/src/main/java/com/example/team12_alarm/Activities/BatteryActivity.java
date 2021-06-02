package com.example.team12_alarm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team12_alarm.R;
import com.example.team12_alarm.Service.BatteryAlarmService;

public class BatteryActivity extends AppCompatActivity {


    static int bat_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        TextView textView = findViewById(R.id.text1);
        Switch switchButton = (Switch) findViewById(R.id.switch1);


        final String[] battery_set = {"5","6","7","8","9","10","11","12","13","14","100"};


        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,battery_set);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bat_set=Integer.parseInt(battery_set[i]);
                textView.setText("배터리 잔량이"+bat_set+"% 미만이 될 시 알람이 울립니다.");
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("배터리 잔량을 선택해주세요");
            }
        });


        Intent alarmService = new Intent(getApplicationContext(), BatteryAlarmService.class);


        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    switchButton.setText("알람 on");
                    startService(alarmService);
                    textView.setText("배터리 잔량이"+bat_set+"% 미만이 될 시 알람이 울립니다.");
                }
                else{
                    switchButton.setText("알람 off");
                    stopService(alarmService);
                    textView.setText("배터리 잔량이"+bat_set+"% 미만이 될 시 알람이 울립니다.");
                }
            }
        });

    }

    public static int getBatSet(){
        return bat_set;

    }


}