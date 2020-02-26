package com.example.voiceprescription;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorScreen extends AppCompatActivity {
    private static final String TAG = "DoctorScreen";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_screen);

        mAuth = FirebaseAuth.getInstance();

        Button newPresBtn = findViewById(R.id.newPresBtn);
        Button signOutBtn = findViewById(R.id.signOutBtn);

        newPresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorScreen.this, SpeechToText.class);
                startActivity(intent);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Log.d(TAG, "onClick: signed Out");
                finish();
            }
        });
    }
}
