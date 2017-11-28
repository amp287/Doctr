package com.group.poop.doctr;

import java.util.Calendar;

/**
 * Created by mkarthik on 11/27/17.
 */

public class MedicalRecord {
    private String apptType;
    private Calendar apptTime;
    private String apptDesc;

    MedicalRecord( String apptType, Calendar apptTime, String apptDesc) {
        this.apptType = apptType;
        this.apptTime = apptTime;
        this.apptDesc = apptDesc;
    }

    public String getApptType() {
        return apptType;
    }

    public Calendar getApptTime() {
        return apptTime;
    }

    public String getApptDesc() {
        return apptDesc;
    }
}
