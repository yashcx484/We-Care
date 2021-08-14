package com.example.womenhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            uid = extras.getString("Uid");
            //The key argument here must match that used in the other activity
        }
    }

    public void onProfile(View view) {
        Intent i = new Intent(getApplicationContext(), profilePage.class);

        startActivity(i);

    }
    public void onHealthProblems(View view) {
        Intent i = new Intent(getApplicationContext(), ShowAllGroup.class);
        i.putExtra("NextPage", "HP");
        i.putExtra("Uid", uid);
        startActivity(i);
    }
    public void onWaterIntake(View view) {
        Intent i = new Intent(getApplicationContext(), AlarmSet.class);
        startActivity(i);
    }
    public void onHealthyHabits(View view) {

        Intent i = new Intent(getApplicationContext(), ShowAllGroup.class);
        i.putExtra("NextPage", "HB");
        i.putExtra("Uid", uid);
        startActivity(i);
    }
}
