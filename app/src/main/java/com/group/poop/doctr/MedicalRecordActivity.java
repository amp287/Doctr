package com.group.poop.doctr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        // Get UID
        Bundle bundle = getIntent().getExtras();
        String uid = (String) bundle.getString("UID");

        mRecycler = (RecyclerView)findViewById(R.id.medicalRecordsRecyclerView);

        // Get Medical Records

        // Array List for the Medical record object
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        // Pass to adapter
        MedicalRecordAdapter mra = new MedicalRecordAdapter( medicalRecords );
        mRecycler.setAdapter(mra);

    }

}
