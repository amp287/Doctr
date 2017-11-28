package com.group.poop.doctr;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.graphics.pdf.PdfDocument;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.Period;
import java.time.LocalDate;

// Fake MedicalRecord class for testing purposes
class MedicalRecord {
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


@TargetApi(26)
public class PDFTester extends AppCompatActivity {

    // Vertical spacing constants
    final int PAD = 15;
    final int WSPACE = 5;

    private Button mButton;

    // Callback for permissions requests
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
            Log.v("PDF","Permission: "+ permissions[0] + "was "+grantResults[0]);
        }
    }

    // Attempt to request storage permission
    public boolean isStoragePermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Log.v("PDF","Permission is granted");
            return true;
        } else {
            Log.v("PDF","Permission is revoked");
            ActivityCompat.requestPermissions(
                    this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Construct user test data
        LocalDate dob = LocalDate.of(1977, 5, 25);
        Date birthday = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());

        final User currentUser = new User("1234567890", "Luke",
                                          "Skywalker", birthday, "male",
                                          68L, 160L, "Porgs, Peanuts","Aspirin");

        // Construct medical record test data
        final ArrayList<MedicalRecord> record_list = new ArrayList<>();
        record_list.add(new MedicalRecord("Teeth Cleaning", Calendar.getInstance(), "Cleaned subject's teeth. Noticed early signs of gingivitis. Recommended daily flossing and fluoride rinse."));
        record_list.add(new MedicalRecord("Amputation", Calendar.getInstance(), "Removed subject's right hand. Attached prosthetic."));
        record_list.add(new MedicalRecord("General Check up", Calendar.getInstance(), "Everything was fine. Bloodwork came back clean, though midi-chlorians were quite high."));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdftester);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton = findViewById(R.id.createPDF);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Call to createPdf. passing in the User object and the MedicalRecord list
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPdf(currentUser,record_list);
            }
        });
    }

    private void createPdf(User user, ArrayList<MedicalRecord> list) {

        // Prompting for permissions
        if (isStoragePermissionGranted()) {
            Log.d("PDF", "File write permissions obtained successfully.");
        } else {
            Log.d("PDF", "Unable to obtain file write permissions.");
        }

        try {
            // Creating the file in Downloads, with the naming convention
            // "LastName_MedicalRecord.pdf"
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(directory, user.getLastName()+"_MedicalRecord.pdf");

            FileOutputStream fOut = new FileOutputStream(file);

            // Creating the PDF
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new
                    PdfDocument.PageInfo.Builder(595, 842, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();

            TextPaint userTextPaint = new TextPaint();
            userTextPaint.setTextSize(13);

            TextPaint medRecTextPaint = new TextPaint();
            medRecTextPaint.setTextSize(12);

            // User Profile Info on PDF
            // Formatting the DOB and calculating current age
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            String birthday = format.format(user.getBirthday().getTime());
            LocalDate c_dob = user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            int age = Period.between(c_dob, now).getYears();

            String userProfileText = (user.getFirstName().toUpperCase() + "  " + user.getLastName().toUpperCase() + "\n"
                    + user.getGender().toUpperCase() + "\n"
                    + "DOB: " + birthday + "\n"
                    + "Age: " + age + "\n"
                    + "Height: " + user.getHeight().toString() + " in" + "    "+ "Weight: " + user.getWeight().toString()+ " lb" + "\n"
                    + "Allergies: " + user.getAllergies().toString() + "\n"
                    + "Medications: " + user.getMedications().toString() + "\n"
            );

            StaticLayout userProfileLayout = new StaticLayout(userProfileText, userTextPaint, canvas.getWidth() - PAD*2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true);

            canvas.save();
            canvas.translate(PAD, PAD);
            userProfileLayout.draw(canvas);
            canvas.restore();

            // Medical Records Info on PDF
            // Keeping track of the height of the current entries
            int height = (userProfileLayout.getHeight())+ WSPACE;


            // Iterating through the List of Medical Records and adding it to the PDF
            for (MedicalRecord record : list) {

                SimpleDateFormat appt_format = new SimpleDateFormat("MM/dd/yyyy");
                String appt_time = appt_format.format(record.getApptTime().getTime());
                String medRec = ("Appointment: " + record.getApptType() + "\n"
                        +"Date: " + appt_time + "\n"
                        +"Description: " + record.getApptDesc() + "\n");

                StaticLayout medRecLayout = new StaticLayout(medRec, medRecTextPaint, canvas.getWidth() - PAD*2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true);

                canvas.save();
                canvas.translate(PAD,PAD+height);
                height += (medRecLayout.getHeight())+ WSPACE;
                medRecLayout.draw(canvas);
                canvas.restore();
            }

            document.finishPage(page);
            document.writeTo(fOut);
            document.close();

            fOut.flush();
            fOut.getFD().sync();
            fOut.close();
            Toast.makeText(PDFTester.this, "PDF Created in Downloads", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.i("error", e.getLocalizedMessage());
        }

    }
}
