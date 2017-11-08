package com.group.poop.doctr;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import java.util.List;

public class Register extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    public final static String EXTRA_MESSAGE = "EmailAddress";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner mMonthSpinner;
    private Spinner mDateSpinner;
    private Spinner mYearSpinner;

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBase = new DataBase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mMonthSpinner = (Spinner) findViewById(R.id.month_spinner);
        mDateSpinner = (Spinner) findViewById(R.id.date_spinner);
        mYearSpinner = (Spinner) findViewById(R.id.year_spinner);
        // Setup
        addItemsToDateSpinner(31);
        addItemsToYearSpinner();

        // Get The email address from Login Form
        String passedEmail;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                passedEmail= null;
            } else {
                passedEmail= extras.getString(EXTRA_MESSAGE);
            }
        } else {
            passedEmail= (String) savedInstanceState.getSerializable(EXTRA_MESSAGE);
        }

        // Set Email address
        mEmailView.setText(passedEmail);

    }

    public void addItemsToDateSpinner(int days) {
        if( (days > 31) || (days <= 0) )
        {
            // TODO - Handle this with and Exception
            // Set default
            days = 31;
        }
        List<String> daysInMonthList = new ArrayList<String>();

        daysInMonthList.add("Select Day");
        for(int i = 1; i <= days; i++)
        {
            daysInMonthList.add(Integer.toString(i));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, daysInMonthList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateSpinner.setAdapter(dataAdapter);
    }

    public void addItemsToYearSpinner() {
        List<String> yearsList = new ArrayList<String>();

        yearsList.add("Select Year");
        int current_year = 2017;
        int yearRange = current_year - 100;
        for(int i = current_year; i > yearRange ; i--)
        {
            yearsList.add(Integer.toString(i));
        }

    // TODO - Create a spinner Listener Function for the mon
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, yearsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(dataAdapter);
}

    public void onGenderRadioButtonClicked(View view)
    {
        // TODO - Not sure if anything needs to be done here.

    }

    public void onUserTypeRadioButtonClicked(View view)
    {
        // TODO - If Doctor is selected, add speciality.
        // TODO - If Patient is pressed, remove speciality.

    }

    public void onClick_AddProfilePicture(View view){
        addProfilePicture(view);
    }

    public void onClick_ProfilePictureButton(View view){
        addProfilePicture(view);
    }

    public void addProfilePicture(View view){
        // TODO - Implement selecting a local picture for prfile Picture.
    }

}

