package com.example.voiceprescription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PatientMain extends AppCompatActivity {
    private static final String TAG = "PatientMain";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Button signOutBtn = findViewById(R.id.signOutBtn);
        final Button reqDocBtn = findViewById(R.id.reqDocBtn);

        Intent intent = getIntent();

        if (intent.hasExtra("com.example.voiceprescription.REQFLAG")) {
            reqDocBtn.setEnabled(false);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Log.d(TAG, "onClick: signed Out");
                finish();
            }
        });

        reqDocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: trying request!");
                Map<String, Object> request = new HashMap<>();
                Map<String, Object> reqFlag = new HashMap<>();
                FirebaseUser user = mAuth.getCurrentUser();
                request.put("from", user.getUid());
                request.put("docUrl", "TBD");
                request.put("timeStamp", new Timestamp(new Date()));
                reqFlag.put("requestForDoctor", true);
                Log.d(TAG, "onClick: objects created.");
                db.collection("requests").document()
                        .set(request)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: request added!");
                        reqDocBtn.setEnabled(false);
                    }
                })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: request failed!");
                    }
                });
                db.collection("users")
                        .document(user.getUid())
                        .set(reqFlag, SetOptions.merge());
            }
        });

    }


}
