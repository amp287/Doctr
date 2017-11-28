package com.group.poop.doctr;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    public void onBindViewHolder(OfferedAppointmentViewHolder holder, int position) {

        holder.speciality.setText(appointments.get(position).speciality);

        holder.doctorName.setText(appointments.get(position).doctorName);

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

        // TODO setup on click listener for cancel button

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class OfferedAppointmentViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView doctorPhoto;
        TextView speciality;
        TextView doctorName;
        TextView price;
        TextView location;
        TextView date;
        TextView startTime;
        TextView endTime;
        Button cancel;

        OfferedAppointmentViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.offered_appointment_card_view);
            doctorPhoto = itemView.findViewById(R.id.offered_appointment_dr_photo);
            speciality = itemView.findViewById(R.id.offered_appointment_speciality);
            doctorName = itemView.findViewById(R.id.offered_appointment_dr_name);
            price = itemView.findViewById(R.id.offered_appointment_price);
            location = itemView.findViewById(R.id.offered_appointment_location);
            date = itemView.findViewById(R.id.offered_appointment_date);
            startTime = itemView.findViewById(R.id.offered_appointment_start);
            endTime = itemView.findViewById(R.id.offered_appointment_end);
            cancel = itemView.findViewById(R.id.offered_appointment_cancel);
        }
    }


}
