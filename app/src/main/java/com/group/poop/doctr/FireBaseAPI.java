package com.group.poop.doctr;

import android.util.Log;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mosborn1987 on 11/28/2017.
 */

public class FireBaseAPI {

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static Doctor doctor;

    // Static initialization
    static{
        requestCurrentDoctor();
    }

    public static void writeCurrentUser(User user)
    {
        // TODO - This API still needs
        //User tempUser = getCurrentUser();

        String userId = FirebaseAuth.getInstance().getUid();

        // TODO - Resolve whether it is a dr or profile

        mDatabase.child("UserProfiles").child(userId).setValue(user);
        mDatabase.child("DoctorProfiles").child(userId).setValue(user);

    }

    private static void requestCurrentDoctor()
    {
        // TODO - An API needs to be created for interfacing with firebase.
        String uid = FirebaseAuth.getInstance().getUid();
        mDatabase.child("DoctorProfiles").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String doctorString = (String) snapshot.getValue().toString();
                doctor = new Doctor(doctorString);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
