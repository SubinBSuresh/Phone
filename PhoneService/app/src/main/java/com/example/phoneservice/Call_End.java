package com.example.phoneservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import ServicePackage.ContactModel;
import ServicePackage.RecentModel;

public class Call_End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);
        ImageButton imgeCall = findViewById(R.id.imgecall);
        imgeCall.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Call_screen.class);
            startActivity(intent);
        });
    }
       /* @Override
        public void onBackPressed() {
            Intent intent = new Intent(getApplicationContext(), Co.class);
            startActivity(intent);*/
            //super.onBackPressed();
    }
