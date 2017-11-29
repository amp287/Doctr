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
    private String uid;

    PDFTester pdf = new PDFTester();

    private List<MedicalRecord> mr_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_medical_record);

        mRecycler = (RecyclerView)findViewById(R.id.p_medicalRecordsRecyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Array List for the Medical record object
        mr_list = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        uid = FirebaseAuth.getInstance().getUid();

        Query query = mDatabase.child("MedicalRecords").child(uid).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mr_list.add(dataSnapshot.getValue(MedicalRecord.class));

                    mRecycler.setAdapter(new MedicalRecordAdapter(mr_list));

                    Query query = FirebaseDatabase.getInstance().getReference().child("UserProfiles").child(uid).orderByKey();
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                user = dataSnapshot.getValue(User.class);
                                //Functionality for Generating PDF
                                createpdf_Button = findViewById(R.id.createPDF);
                                createpdf_Button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        pdf.createPdf(user,mr_list);
                                    }
                                });
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError){}
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

}
