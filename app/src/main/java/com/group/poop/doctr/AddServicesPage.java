package com.group.poop.doctr;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddServicesPage extends AppCompatActivity {

    // Select Service
    private Spinner generalPracticeSpinner;
    private Spinner servicesSpinner;
    private TextView serviceTextView;

    // Hours
    private TextView hoursTextView;
    private Spinner fromTimeSpinner;
    private Spinner toTimeSpinner;

    // Cost
    private EditText costAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Select Service Spinners
        generalPracticeSpinner = (Spinner) findViewById(R.id.generalPracticeSpinner);
        servicesSpinner = (Spinner) findViewById(R.id.servicesSpinner);

        // Select Hours Spinners
        fromTimeSpinner = (Spinner) findViewById(R.id.fromTimeSpinner);
        toTimeSpinner = (Spinner) findViewById(R.id.toTimeSpinner);

        serviceTextView = (TextView) findViewById(R.id.serviceTextView);
        costAmountEditText = (EditText) findViewById(R.id.costAmountEditText);

        generalPracticeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected Item String
                String selectedText = getSpinnerSelectedStringValue(generalPracticeSpinner);

                // Retrieve the "speciality" or services list.
                ArrayList<String> servicesList  = getServiceArrayName(selectedText);

                // Populate the services avaliable spinner
                populateServicesSpinner(servicesList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }
        });
    }

    public void onClickBackButton(View view){
        AddServicesPage.this.finish();
    }

    public String getSpinnerSelectedStringValue(Spinner spinner)
    {
        return spinner.getSelectedItem().toString();
    }

    private ArrayList<String> getServiceArrayName(String generalPractice )
    {
        String[] stringArray = null;
        ArrayList<String> stringArrayList = new ArrayList<String>();

        if( generalPractice.contains(( "Primary Health Care" )))
        {
            stringArray = getResources().getStringArray(R.array.primaryhealthcare_array);

        }else if( generalPractice.contains(( "Dental Care" )))
        {
            stringArray = getResources().getStringArray(R.array.dentalcare_array);
        }else if( generalPractice.contains(( "Specialty Care" )))
        {
            stringArray = getResources().getStringArray(R.array.specialtycare_array);
        }

        // Nothing Selected
        if(stringArray == null)
        {
            return stringArrayList;
        }

        // Add each service to ArrayList
        for(String str: stringArray)
        {
            stringArrayList.add(str);
        }

        return stringArrayList;

    }
    private void populateServicesSpinner(ArrayList<String> servicesList)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, servicesList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicesSpinner.setAdapter(adapter);

//        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(servicesSpinner, servicesList);
//        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        gameKind.setAdapter(gameKindArray);

    }

    public void onClickAddServiceButton(View view)
    {
        // TODO - Verify that all of the inputs make sence.

        // TODO - If One single item does not make sense, setError and don't "add service".

        // Example of how to set an error
        serviceTextView.setError("Yo! This is an error message!");

        // Example of how to clear an error
        //serviceTextView.setError(null);

        // TODO - If all pass, save service to dataBase. -> Create a class called "Service" or
        // TODO - something like that. So that it can be passed around.

        // TODO - If saved to database is successful, close this window, else warn the user.
    }

}

