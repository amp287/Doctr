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

    private RecyclerView mRecycler;
    private Button createpdf_Button;
    private Button saveapptdesc_Button;
    PDFTester pdf = new PDFTester();

    private List<MedicalRecord> mr_list;
    private List<MedicalRecord> mr_list_test;
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

        // Get UID
        // TODO: This was giving me a error so I had to comment it out.
        /*Bundle bundle = getIntent().getExtras();
        String uid = bundle.getString(UID_PARAM);
        boolean showMr = bundle.getBoolean(SHOW_MR_PARAM);
        if(!showMr) return;


        Query query = FirebaseDatabase.getInstance().getReference().child("MedicalRecords").child(uid).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mr_list.add(dataSnapshot.getValue(MedicalRecord.class));

                    mRecycler.setAdapter(new MedicalRecordAdapter(mr_list));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

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
        //TODO : remove _test after this is functional
        //TODO : Need to grab User object (patient)
        createpdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdf.createPdf(user_test,mr_list_test);
            }
        });


    }

}
