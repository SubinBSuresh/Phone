package com.example.phoneservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Call_End extends AppCompatActivity {
    private ImageButton ImgeCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);
        ImgeCall = findViewById(R.id.imgecall);
        ImgeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Call_screen.class);
                startActivity(intent);
            }
        });
    }
}