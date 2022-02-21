package com.example.phonehmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ServicePackage.RecentModel;

public class Calling_Screen extends AppCompatActivity {
    Timer timer;
    private Chronometer chronometer,tvNam,tvNum;
    private boolean running;
    RecentModel recentModel=new RecentModel();
    List<RecentModel> recentModelList= new ArrayList<>();
    String name,number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_screen);
        ImageButton btnEnd = findViewById(R.id.btnEnd);
        chronometer = findViewById(R.id.tvTimer);
        timer = new Timer();

        if (!running) {
            chronometer.start();
            running = true;
        }
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            name = extras.getString("name");
            number = extras.getString("number");
            tvNam.setText(name);
           tvNum.setText(number);

        } else {
            // handle case
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log.e("name",""+dtf.format(now));
        recentModel.setName(name);
        recentModel.setNumber(number);
        recentModel.setDate(dtf.format(now));
        recentModelList.add(recentModel);
        try {
            MainActivity.getAidl().addToRecent(recentModelList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        Toast.makeText(getApplicationContext(),name+number,Toast.LENGTH_SHORT).show();
        btnEnd.setOnClickListener(v -> {
            chronometer.stop();


            Toast.makeText(getApplicationContext(), "call ended", Toast.LENGTH_LONG).show();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(getApplicationContext(), Call_end.class);
                    intent.putExtra("nam", tvNam.getText().toString());
                    intent.putExtra("num", tvNam.getText().toString());
                    startActivity(intent);

                }
            }, 4000);


        });
    }
}