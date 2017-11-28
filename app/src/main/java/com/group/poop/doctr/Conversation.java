package com.group.poop.doctr;

/**
 * Created by amp on 11/24/17.
 */

public class Conversation {
    private String doctorUID;
    private String patientUID;
    private String patientName;
    private String doctorName;

    public Conversation(String doctorUID, String patientUID, String doctorName, String patientName){
        this.doctorUID = doctorUID;
        this.patientUID = patientUID;
        this.doctorName =  doctorName;
        this.patientName = patientName;
    }

    public Conversation(){

    }

    public String getDoctorUID(){return this.doctorUID;}
    public String getPatientUID(){return this.patientUID;}
    public String getDoctorName(){return this.doctorName;}
    public String getPatientName(){return this.patientName;}
}
