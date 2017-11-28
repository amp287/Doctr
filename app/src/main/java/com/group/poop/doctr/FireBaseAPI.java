package com.group.poop.doctr;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mosborn1987 on 11/28/2017.
 */

public class FireBaseAPI {

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public static void writeCurrentUser(User user)
    {
        // TODO - This API still needs
        String userId = FirebaseAuth.getInstance().getUid();

        // TODO - Resolve whether it is a dr or profile
        
        mDatabase.child("UserProfiles").child(userId).setValue(user);
        mDatabase.child("DoctorProfiles").child(userId).setValue(user);

    }

    public static User getCurrentUser()
    {
        // TODO - An API needs to be created for interfacing with firebase.
        String uid = FirebaseAuth.getInstance().getUid();

//        ValueEventListener listener = new ValueEventListener() {
//            // ...
//        };
//        mDatabase.addListenerForSingleValueEvent(listener);
//
//        String uid = FirebaseAuth.getInstance().getUid();

//        String userName2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        // Example of reading from the database
//        ref = FirebaseDatabase.getInstance().getReference();
//        String uid = FirebaseAuth.getInstance().getUid();
//        DatabaseReference drNameRef = ref.child("DoctorProfiles").child(uid);

//        drNameRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Doctor dr = dataSnapshot.getValue(Doctor.class);
//                String firstName = dr.getFirstName();
//                String lastName = dr.getLastName();
//
//                userNameTextView.setText("Dr. " + dr.getFirstName() + " " + dr.getLastName());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

        return null;
    }

}
