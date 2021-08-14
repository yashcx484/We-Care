package com.example.womenhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmSet extends AppCompatActivity implements  View.OnClickListener {
    private int notificationId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        findViewById(R.id.setBtn).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.cancelBtn).setOnClickListener((View.OnClickListener) this);
    }



    public void onClick(View view) {
        EditText editText = findViewById(R.id.editTextGetMsg);
        EditText editText1 = findViewById(R.id.editTextAddInterval);
        TimePicker timePicker = findViewById(R.id.timePicker);
//        int timeInt = Integer.parseInt(editText1.getText().toString());
        float timeInt = Float.parseFloat(editText1.getText().toString());
        int timeInterval= (int)(timeInt*60000);
        // Intent
        Intent intent = new Intent(AlarmSet.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());


        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                AlarmSet.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
//                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Reminder Set Succesfully!!", Toast.LENGTH_SHORT).show();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+timeInterval,timeInterval,pendingIntent);

                //finish();
                //System.exit(0);
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
