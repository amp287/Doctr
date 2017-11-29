package com.group.poop.doctr;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mkarthik on 11/27/17.
 */

public class MedicalRecord {
    private String apptType;
    private Date apptTime;
    private String apptDesc;

    MedicalRecord( String apptType, Date apptTime, String apptDesc) {
        this.apptType = apptType;
        this.apptTime = apptTime;
        this.apptDesc = apptDesc;
    }

    public String getApptType() {
        return apptType;
    }

    public Date getApptTime() {
        return apptTime;
    }

    public String getApptDesc() {
        return apptDesc;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public void setApptTime(Date apptTime) {
        this.apptTime = apptTime;
    }

    public void setApptDesc(String apptDesc) {
        this.apptDesc = apptDesc;
    }
}
