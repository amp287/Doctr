package com.group.poop.doctr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorMedicalRecordActivity extends AppCompatActivity {

    // Recycler
    private RecyclerView mRecycler;

    // Buttons
    private Button createpdf_Button;
    private Button saveapptdesc_Button;

    // PDF Tester
    PDFTester pdf = new PDFTester();

    // User Info
    private User user;
    private String uid;
    private List<MedicalRecord> mr_list;

    // Constants
    public static final String UID_PARAM = "UID";
    public static final String SHOW_MR_PARAM = "SHOWMR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        mRecycler = (RecyclerView)findViewById(R.id.medicalRecordsRecyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Array List for the Medical record object
        mr_list = new ArrayList<MedicalRecord>();

        // Initialize Views
        createpdf_Button = findViewById(R.id.createPDF);
        saveapptdesc_Button = findViewById(R.id.saveApptDesc);

        saveapptdesc_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int i = 0;

            }
        });



        // Get UID
        // TODO: This was giving me a error so I had to comment it out.
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString(UID_PARAM);
        boolean showMr = bundle.getBoolean(SHOW_MR_PARAM);

        if(showMr){

            // Get All Medical Records
            Query query = FirebaseDatabase.getInstance().getReference().child("MedicalRecords").child(uid).orderByKey();

            //
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

}
