package com.group.poop.doctr;

import java.util.Date;

/**
 * Created by Luke on 11/27/2017.
 */

public class Appointment {
    String location;
    Date date;
    Date startTime;
    Date endTime;
    Long price;
    String speciality; // will probably correspond to sub-speciality; it is the type of appointment e.g. tooth filling or heart surgery
    String doctorUID; // do not include in ui
    String description;

    // Zero parameter constructor for Firebase
    public Appointment() {

    }

    public Appointment(String location, Date date, Date startTime, Date endTime, Long price, String speciality, String doctorUID, String description) {
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.speciality = speciality;
        this.doctorUID = doctorUID;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDoctorUID() {
        return doctorUID;
    }

    public void setDoctorUID(String doctorUID) {
        this.doctorUID = doctorUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
