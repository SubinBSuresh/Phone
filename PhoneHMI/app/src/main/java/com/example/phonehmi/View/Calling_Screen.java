package com.example.phonehmi.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ServicePackage.RecentModel;

public class Calling_Screen extends AppCompatActivity {
    Timer timer;

    RecentModel recentModel = new RecentModel();
    List<RecentModel> recentModelList = new ArrayList<>();
    String name = DialerFragment.tvCallSelectedName.getText().toString(), number = DialerFragment.tvCallSelectedNumber.getText().toString();

    private Chronometer chronometer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_screen);
        ImageButton btnEnd = findViewById(R.id.btnEnd);
        TextView tvNam = findViewById(R.id.tvName);
        TextView tvNum = findViewById(R.id.tvNumb);
        chronometer = findViewById(R.id.tvTimer);
        timer = new Timer();


        tvNam.setText(name);
        tvNum.setText(number);

        if (!running) {
            chronometer.start();
            running = true;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log.e("name", "" + dtf.format(now));
        recentModel.setName(name);
        recentModel.setNumber(number);
        recentModel.setDate(dtf.format(now));
        recentModelList.add(recentModel);
        try {
            MainActivity.getAidl().addToRecent(recentModelList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        btnEnd.setOnClickListener(v -> {
            chronometer.stop();


            Toast.makeText(getApplicationContext(), "call ended", Toast.LENGTH_LONG).show();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Call_end.class);


                    startActivity(intent);

                }
            }, 4000);


        });
    }

}