package com.group.poop.doctr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PatientMedicalRecordActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private Button createpdf_Button;
    private User user = null;

    PDFTester pdf = new PDFTester();

    private List<MedicalRecord> mr_list;
    private List<MedicalRecord> mr_list_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_medical_record);

        mRecycler = (RecyclerView)findViewById(R.id.p_medicalRecordsRecyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Array List for the Medical record object
        mr_list = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getUid();
        Query query = mDatabase.child("MedicalRecords").child(uid).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mr_list.add(dataSnapshot.getValue(MedicalRecord.class));
                    mRecycler.setAdapter(new MedicalRecordAdapter(mr_list));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        ///HARDCODED MEDICAL RECORDS, NEEDS TO BE REMOVED
        // TODO: def need to remove all this after MR are created in the Firebase.
        Calendar cal = Calendar.getInstance();
        cal.set(1977,5,23);
        final User user_test = new User("1234567890", "Luke",
                "Skywalker", cal.getTime(), "male",
                68L, 160L, "Porgs, Peanuts","Space Aspirin");
        mr_list_test = new ArrayList<>();
        mr_list_test.add(new MedicalRecord("Teeth Cleaning", Calendar.getInstance().getTime(), "Cleaned subject's teeth. Noticed early signs of gingivitis. Recommended daily flossing and fluoride rinse."));
        mr_list_test.add(new MedicalRecord("Amputation", Calendar.getInstance().getTime(), "Removed subject's right hand. Attached prosthetic."));
        mr_list_test.add(new MedicalRecord("General Check up", Calendar.getInstance().getTime(), "Everything was fine. Bloodwork came back clean, though midi-chlorians were quite high."));
        mRecycler.setAdapter(new MedicalRecordAdapter(mr_list_test));
        //HARDCODED MEDICAL RECORDS, NEEDS TO BE REMOVED

        //Functionality for Generating PDF
        createpdf_Button = findViewById(R.id.createPDF);

        mDatabase.child("UserProfiles").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        createpdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : remove _test after this is functional
                pdf.createPdf(user_test,mr_list_test);
            }
        });

    }

}
