package com.example.phoneservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Call_screen extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running;
    private ImageButton BtnEnd;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_screen);
        BtnEnd = findViewById(R.id.btnEnd);
        chronometer = findViewById(R.id.tvTimer);


        if (!running) {
            chronometer.start();
            running = true;
        }
        BtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                Toast.makeText(getApplicationContext(),"call ended",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Call_End.class);
                startActivity(intent);

            }

        });
    }
}