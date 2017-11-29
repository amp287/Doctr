package com.group.poop.doctr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    private List<MedicalRecord> mr_list;

    public static final String UID_PARAM = "UID";
    public static final String SHOW_MR_PARAM = "SHOWMR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        // Get UID
        Bundle bundle = getIntent().getExtras();
        String uid = bundle.getString(UID_PARAM);
        boolean showMr = bundle.getBoolean(SHOW_MR_PARAM);

        mRecycler = (RecyclerView)findViewById(R.id.medicalRecordsRecyclerView);

        // Get Medical Records

        // Array List for the Medical record object
       mr_list = new ArrayList<MedicalRecord>();

        if(!showMr)
            return;

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
        });

    }

}
