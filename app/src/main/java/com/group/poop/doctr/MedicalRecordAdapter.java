package com.group.poop.doctr;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Luke on 11/27/2017.
 */

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder> {

    List<MedicalRecord> medicalRecords;

    MedicalRecordAdapter(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public MedicalRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_card, parent, false);
        MedicalRecordViewHolder uvh = new MedicalRecordViewHolder(view);
        return uvh;
    }

    @Override
    public void onBindViewHolder(MedicalRecordViewHolder holder, int position) {
        // Type
        String type = medicalRecords.get(position).getApptType();
        holder.type.setText(type);

        // Time
        String time = medicalRecords.get(position).getApptTimeString();
        holder.time.setText(time);

        // Description
        String description = medicalRecords.get(position).getApptDesc();
        holder.discription.setText(description);

    }

    @Override
    public int getItemCount() {
        return medicalRecords.size();
    }

    public static class MedicalRecordViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView type;
        TextView time;
        TextView discription;

        MedicalRecordViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.patient_card_view);
            type = itemView.findViewById(R.id.type);
            time = itemView.findViewById(R.id.time);
            discription = itemView.findViewById(R.id.discription);

        }
    }
}
