package com.group.poop.doctr;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Luke on 11/28/2017.
 */

public class OfferedAppointmentAdapter extends RecyclerView.Adapter<OfferedAppointmentAdapter.OfferedAppointmentViewHolder> {

    List<Appointment> appointments;

    OfferedAppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public OfferedAppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offered_appointment_card, parent, false);
        OfferedAppointmentViewHolder oavh = new OfferedAppointmentViewHolder(view);


        return oavh;
    }

    @Override
    public void onBindViewHolder(OfferedAppointmentViewHolder holder, final int position) {

        holder.speciality.setText(appointments.get(position).speciality);

        holder.price.setText("$" + appointments.get(position).price.toString());

        holder.location.setText(appointments.get(position).location);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = dateFormat.format(appointments.get(position).getDate().getTime());
        holder.date.setText(dateString);

        SimpleDateFormat startFormat = new SimpleDateFormat("hh:mm aaa");
        String startString = startFormat.format(appointments.get(position).getStartTime().getTime());
        holder.startTime.setText(startString);

        SimpleDateFormat endFormat = new SimpleDateFormat("hh:mm aaa");
        String endString = endFormat.format(appointments.get(position).getEndTime().getTime());
        holder.endTime.setText(endString);

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open patient MR

                // notify patient

                // set appt flag to accepted
                appointments.get(position).setAccepted(1);
                Log.e("value " , "" + appointments.get(position).getAccepted());
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open patient MR

                // notify patient

                // set appt flag to rejected
                appointments.get(position).setAccepted(-1);
                Log.e("value " , "" + appointments.get(position).getAccepted());
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class OfferedAppointmentViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView speciality;
        TextView price;
        TextView location;
        TextView date;
        TextView startTime;
        TextView endTime;
        Button accept;
        Button reject;

        OfferedAppointmentViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.offered_appointment_card_view);
            speciality = itemView.findViewById(R.id.offered_appointment_speciality);
            price = itemView.findViewById(R.id.offered_appointment_price);
            location = itemView.findViewById(R.id.offered_appointment_location);
            date = itemView.findViewById(R.id.offered_appointment_date);
            startTime = itemView.findViewById(R.id.offered_appointment_start);
            endTime = itemView.findViewById(R.id.offered_appointment_end);
            accept = itemView.findViewById(R.id.offered_appointment_accept);
            reject = itemView.findViewById(R.id.offered_appointment_reject);

            //Buttons
//
//            accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i = 0;
//                Log.e("Button " ,"accept");
//                // open patient MR
//
////                // notify patient
//
////                // set appt flag to accepted
//            }
//        });
////
            reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                Log.e("Button " ,"reject");

////                //appointment set to rejected

////                //notify patient
            }
        });

        }
    }


}
