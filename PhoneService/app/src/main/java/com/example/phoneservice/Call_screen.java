package com.example.phoneservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Call_screen extends AppCompatActivity {
    //    private TextView textViewName;
    Timer timer;
    private Chronometer chronometer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_screen);
        ImageButton btnEnd = findViewById(R.id.btnEnd);
        chronometer = findViewById(R.id.tvTimer);
        timer = new Timer();

        if (!running) {
            chronometer.start();
            running = true;
        }
        btnEnd.setOnClickListener(v -> {
            chronometer.stop();
            Toast.makeText(getApplicationContext(), "call ended", Toast.LENGTH_LONG).show();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Call_End.class);
                    startActivity(intent);
                }
            }, 4000);


        });
    }
}