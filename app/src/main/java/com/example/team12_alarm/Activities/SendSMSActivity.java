package com.example.team12_alarm.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team12_alarm.R;

public class SendSMSActivity extends AppCompatActivity {
    private static boolean isSelected;
    private static String phoneNum;
    private static String smsText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        EditText edit1 = (EditText) findViewById(R.id.Edit1);
        EditText edit2 = (EditText) findViewById(R.id.Edit2);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Switch switchButton = (Switch) findViewById(R.id.switch1);


        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                phoneNum = edit1.getText().toString();
                smsText = edit2.getText().toString();
                Toast.makeText(getApplicationContext(), "문자메세지 설정 저장완료", Toast.LENGTH_SHORT).show();
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchButton.setText("활성화");
                    isSelected = true;
                    if (AlarmActivity.getFlag() && isSelected) {
                        Toast.makeText(getApplicationContext(), "문자메세지 전송", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNum));
                        intent.putExtra("sms_body", smsText);
                        startActivity(intent);
                    }
                } else {
                    switchButton.setText("비활성화");
                    isSelected = false;
                }
            }
        });


    }

    public static String getPhoneNum() {
        return phoneNum;
    }

    public static String getSmsText() {
        return smsText;
    }

    public static boolean getIsSelected() {
        return isSelected;
    }


}