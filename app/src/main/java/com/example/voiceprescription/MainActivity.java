package com.example.voiceprescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button signUpEmailBtn = findViewById(R.id.signUpEmailBtn);
        Button signInEmailBtn = findViewById(R.id.signInEmailBtn);
        Button signInGoogleBtn = findViewById(R.id.signInGoogleBtn);

        signInEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("com.example.voiceprescription.LOGIN", true);
                Log.d(TAG, "onClick: Signing In");
                startActivity(intent);
            }
        });

        signUpEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("com.example.voiceprescription.LOGIN", false);
                Log.d(TAG, "onClick: Signing In");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onStart: user- " + user.getEmail());
            Intent intent = new Intent(getApplicationContext(), PatientMain.class);
            intent.putExtra("com.example.voiceprescription.LOGIN", true);
            startActivity(intent);
        }

    }
}
