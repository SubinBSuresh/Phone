package com.example.phonehmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Call_end extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);
        ImageButton imgeCall = findViewById(R.id.imgecall);
        ImageButton imgeBack = findViewById(R.id.imgeback);
        imgeCall.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Calling_Screen.class);
            startActivity(intent);
        });
        imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DialerFragment.class);
                startActivity(intent);
            }
        });
    }


}