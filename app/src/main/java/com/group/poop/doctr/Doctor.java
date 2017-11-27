package com.group.poop.doctr;

/**
 * Created by amp on 11/26/2017.
 */

public class Doctor {

    private String uid;
    private String firstName;
    private String lastName;
    private String specialization;
    private String subSpecialization;

    public Doctor(String uid, String firstName, String lastName,
                  String specialization,
                  String subSpecialization){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.subSpecialization = subSpecialization;
    }

    public Doctor(){

    }

    public String getUid() {return this.uid;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getSpecialization() {return specialization;}
    public String getSubSpecialization() {return subSpecialization;}
}
