package com.group.poop.doctr;

/**
 * Created by AMP on 11/28/2017.
 */

public class ConversationListFiller {
    public String doctor;
    public String patient;
    public String doctorUID;
    public String patientUID;
    public String nameToShow;
    public ChatMessage last;

    public ConversationListFiller(String doctor, String patient, String doctorUID, String patientUID, ChatMessage last){
        this.doctor = doctor;
        this.patient = patient;
        this.doctorUID = doctorUID;
        this.patientUID = patientUID;
        this.last = last;
    }

}
