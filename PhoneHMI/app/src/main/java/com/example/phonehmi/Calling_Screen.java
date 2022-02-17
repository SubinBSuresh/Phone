package com.example.phonehmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Calling_Screen extends AppCompatActivity {
    Timer timer;
    private Chronometer chronometer;
    private boolean running;

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