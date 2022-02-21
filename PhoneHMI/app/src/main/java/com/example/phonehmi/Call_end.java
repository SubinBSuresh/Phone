package com.example.phonehmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Call_end extends AppCompatActivity {

    private Object Fragment;
    String nam,num;
    private TextView tvN,tvNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);
        ImageButton imgeCall = findViewById(R.id.imgecall);
        ImageButton imgeBack = findViewById(R.id.imgeback);
        tvN = findViewById(R.id.tvcallname);
        tvNu = findViewById(R.id.tvcallnum);
        imgeCall.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Calling_Screen.class);
            startActivity(intent);
        });
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            nam = extras.getString("nam");
            num = extras.getString("numb");
            tvN.setText(nam);
            tvNu.setText(num);

        } else {
            // handle case
        }
        imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


}