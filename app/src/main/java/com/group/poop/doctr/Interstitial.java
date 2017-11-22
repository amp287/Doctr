package com.group.poop.doctr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Interstitial extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    // TODO handle users that closed the app while signing up

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(Interstitial.this, "Already Logged in!", Toast.LENGTH_SHORT).show();
            DatabaseReference doctorRef = mDatabase.child("doctorsList").child(currentUser.getUid());
            // Read from the database
            doctorRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Boolean isDoctor = false;
                    isDoctor = dataSnapshot.exists();
                    if (isDoctor) {
                        Intent intent = new Intent(Interstitial.this, DoctorHome.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Interstitial.this, UserHome.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } else {
            Intent intent = new Intent(Interstitial.this, LoginPage.class);
            startActivity(intent);
        }
    }
}
